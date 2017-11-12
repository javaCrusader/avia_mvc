package service;

import model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.FlightRepository;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;


    public boolean insert(Flight flight) {
        return flightRepository.save(flight) != null;
    }

    public boolean update(Flight flight) {
        Flight old = flightRepository.findOne(flight.getId());
        if (old == null)
            return false;
        old.setName(flight.getName());
        old.setTicketSet(flight.getTicketSet());
        return flightRepository.save(old) != null;
    }

    public List<Flight> get(String name) {
        return flightRepository.findByName(name);
    }

    public List<Flight> getAll() {
        return flightRepository.findAll();
    }


    public Flight get(Integer id) {
        return flightRepository.findOne(id);
    }
}
