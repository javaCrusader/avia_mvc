package service;

import model.City;
import model.Country;
import model.Flight;
import model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.CityRepository;
import repository.CountryRepository;
import repository.FlightRepository;
import repository.TicketRepository;

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


    public boolean insert(Flight flight) {
        return flightRepository.save(flight) != null;
    }

    public boolean insertTicket (Ticket ticket) {
        return ticketRepository.save(ticket) !=null;
    }

    public void deleteTicket (Integer idTicket) {
        ticketRepository.delete(idTicket);
    }


    public boolean update(Flight flight) {
        Flight old = flightRepository.findOne(flight.getId());
        if (old == null)
            return false;
        old.setName(flight.getName());
        old.setAircraft(flight.getAircraft());
        old.setDone(flight.isDone());
        old.setStart(flight.getStart());
        old.setEnd(flight.getEnd());
        old.setTicketList(flight.getTicketList());
        return flightRepository.save(old) != null;
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

    @Transactional
    public boolean delete(Integer id) {
        if (flightRepository.findOne(id) == null)
            return false;
        flightRepository.delete(id);
        return true;
    }

}
