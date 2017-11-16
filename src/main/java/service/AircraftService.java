package service;

import model.Aircraft;
import model.AircraftClassData;
import model.AircraftPlaceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.AircraftClassRepository;
import repository.AircraftPlaceRepository;
import repository.AircraftRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AircraftService {

    @Autowired
    private AircraftClassRepository aircraftClassRepository;

    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    AircraftPlaceRepository placeRepository;


    Logger logger = LoggerFactory.getLogger(AircraftService.class);

    @Transactional
    public boolean constructInsert(Map<String, Integer> classCapacityMap, String aircraftName) {
        Aircraft yak = new Aircraft();
        return insert(yak.setName(aircraftName)
                .setPlaceInfoList(classCapacityMap.entrySet().stream()
                        .map(entry -> new AircraftPlaceInfo()
                                .setCapacity(entry.getValue())
                                .setAirClass(aircraftClassRepository.findOneByName(entry.getKey())
                                        .orElseGet(() -> aircraftClassRepository.save(new AircraftClassData().setName(entry.getKey()).setPlaceDataSet(null))))
                                .setAircraft(yak))
                        .collect(Collectors.toList())));

    }


    public boolean insert(Aircraft aircraft) {
        return aircraftRepository.save(aircraft) != null;
    }

    public AircraftPlaceInfo getPlaceInfo(Integer id) {
        return placeRepository.findOne(id);
    }

    public List<Aircraft> get(String name) {
        return aircraftRepository.findByName(name);
    }

    public List<Aircraft> getAll() {
        return aircraftRepository.findAll();
    }

    public List<Aircraft> getAllFree() {
        return aircraftRepository.findAll().stream().filter(aircraft -> aircraft.getFlight() == null).collect(Collectors.toList());
    }

    public List<AircraftClassData> getAllClasses() {
        return aircraftClassRepository.findAllByOrderByIdAsc();
    }

    public Aircraft get(Integer id) {
        return aircraftRepository.findOne(id);
    }

    @Transactional
    public boolean update(Aircraft aircraft) {
        Aircraft old = aircraftRepository.findOne(aircraft.getId());
        if (old == null)
            return false;
        old.setName(aircraft.getName());
        old.setPlaceInfoList(aircraft.getPlaceInfoList());
        return aircraftRepository.save(old) != null;
    }

    @Transactional
    public boolean delete(Integer id) {
        Aircraft old = aircraftRepository.findOne(id);
        if (old == null)
            return false;
        aircraftRepository.delete(old.getId());
        return true;
    }

}
