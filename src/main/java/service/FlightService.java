package service;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

    public List<City> getAllCities() {
        return cityRepository.findAllByOrderByNameAsc();
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAllByOrderByNameAsc();
    }

    public List<City> getCitiesByCountry(String country) {
        return cityRepository.findAllByCountryNameEquals(country);
    }

    public Flight get(Integer id) {
        return flightRepository.findOne(id);
    }

    public Ticket getTicket(Integer id) {
        return ticketRepository.findOne(id);
    }


    public boolean addIssueForTicket(List<Ticket> ticketList) {
        for (Ticket ticket : ticketList) {
            Issue issue = new Issue();
            issue.setCreated(new Date(System.currentTimeMillis()));
            issue.setUser(ticket.getUser());
            issue.setProblem("user id: " + ticket.getUser().getId() + " lost ticket id: " + ticket.getId() + " from " + ticket.getFlight().getStartCity().getName()
                    + " to " + ticket.getFlight().getEndCity().getName() + " on date " + ticket.getFlight().getStart().toString()
                    + " cost " + ticket.getFactCost() +"    \n" + ticket.toString());

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
          /*  if (!this.deleteTicket(ticket.getId()) ) ;
                return false;*/
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
    public boolean persistTicket(Ticket ticket, Flight flight, User user, AircraftPlaceInfo place ) {
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
