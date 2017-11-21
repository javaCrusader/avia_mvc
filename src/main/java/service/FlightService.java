package service;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.*;
import result.search.SearchParam;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    UserService userService;

    @Autowired
    AircraftService aircraftService;

    public boolean insert(Flight flight) {
        return flightRepository.save(flight) != null;
    }


    public List<Flight> get(String name) {
        return flightRepository.findByName(name);
    }

    public List<Flight> getAll() {
        return flightRepository.findAll();
    }

    public List<Flight> getAllBySearchParams(SearchParam param) {
        return flightRepository.findAll().stream().filter(flight ->
                flight.getStartCity().getId().equals(param.getStartCity().getId())
                        && flight.getEndCity().getId().equals(param.getEndCity().getId())
                        && param.getStartDate().getTime() <= flight.getStart().getTime()
                        && param.getEndDate().getTime() >= flight.getEnd().getTime()

        ).collect(Collectors.toList());
    }

    public List<City> getAllCities() {
        return cityRepository.findAllByOrderByNameAsc();
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAllByOrderByNameAsc();
    }

    public List<City> getCitiesByCountry(String country) {
        return cityRepository.findAllByCountryNameEquals(country);
    }


    /**
     * @param id сразу переводим дату в текстовый формат для дальнейших манипуляций view.
     * @return
     */
    public Flight get(Integer id) {
        return flightRepository.findOne(id);
    }

    public Flight getAndFormatDate(Integer id) {
        Flight flight = flightRepository.findOne(id);
        if (flight != null && flight.getStart() != null && flight.getEnd() != null) {
            flight.setStartStringDate(flight.getDateFmt().format(flight.getStart()));
            flight.setEndStringDate(flight.getDateFmt().format(flight.getEnd()));
        }
        return flight;
    }

    public boolean parseDateFromString(Flight flight) {
        if (flight == null)
            return false;
        try {
            flight.setStart(flight.getDateFmt().parse(flight.getStartStringDate()));
            flight.setEnd(flight.getDateFmt().parse(flight.getEndStringDate()));
        } catch (ParseException e) {
            return false;
        }
        return true;
    }


    public Ticket getTicket(Integer id) {
        return ticketRepository.findOne(id);
    }

    public boolean addIssueForTicket(List<Ticket> ticketList) {
        for (Ticket ticket : ticketList) {
            Issue issue = new Issue();
            issue.setCreated(new Date(System.currentTimeMillis()));
            issue.setUser(ticket.getUser());
            issue.setProblem(ticket.toString());

            Iterator<Ticket> itUserTicketList = ticket.getUser().getTicketsList().iterator();
            while (itUserTicketList.hasNext()) {
                Ticket iter = itUserTicketList.next();
                if (iter.getId() == ticket.getId()) {
                    itUserTicketList.remove();
                }
            }
            userService.saveCompleteObject(ticket.getUser());
            if (issueRepository.save(issue) == null)
                return false;
        }
        return true;
    }

    public boolean deleteTicket(Integer id) {
        Ticket old = ticketRepository.findOne(id);
        if (old == null)
            return false;
        ticketRepository.delete(id);

        return true;
    }

    @Transactional
    public boolean persistTicket(Ticket ticket, Flight flight, User user, AircraftPlaceInfo place) {
        if (ticketRepository.save(ticket) == null)
            return false;
        flight.addTicket(ticket);
        user.addTicket(ticket);
        place.addTicket(ticket);
        if (this.insert(flight) && userService.saveCompleteObject(user) && aircraftService.savePlaceInfo(place))
            return true;
        return false;
    }

    @Transactional
    public boolean delete(Integer id) {
        Flight flight = flightRepository.findOne(id);
        if (flight == null)
            return false;
        if (!flight.getTicketList().isEmpty()) {
            addIssueForTicket(flight.getTicketList());
        }
        flight.getAircraft().setFlight(null);
        if (!aircraftService.insert(flight.getAircraft()))
            return false;
        flightRepository.delete(id);
        return true;
    }

}
