package service;

import model.City;
import model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.CityRepository;
import repository.FlightRepository;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    CityRepository cityRepository;


    public boolean insert(Flight flight) {
       return flightRepository.save(flight) != null;
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
