package service;

import model.Aircraft;
import model.AircraftClassData;
import model.AircraftPlaceInfo;
import model.CrewMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.AircraftClassRepository;
import repository.AircraftPlaceRepository;
import repository.AircraftRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Service
public class AircraftService {

    @Autowired
    private AircraftClassRepository aircraftClassRepository;

    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private AircraftPlaceRepository aircraftPlaceRepository;

    Logger logger = LoggerFactory.getLogger(AircraftService.class);

    @Transactional
    public boolean constructInsert(Map<String, Integer> classCapacityMap, String aircraftName) {
        List<AircraftClassData> classDataList;
        AircraftClassData classData;
        boolean isOk;
        Aircraft aircraft = new Aircraft(aircraftName);
        isOk = aircraftRepository.save(aircraft) != null;
        for (Map.Entry<String, Integer> pair : classCapacityMap.entrySet()) {
            AircraftPlaceInfo placeInfo = new AircraftPlaceInfo();
            classDataList = aircraftClassRepository.findByName(pair.getKey());
            if (classDataList.isEmpty()) {
                classData = new AircraftClassData();
                classData.setName(pair.getKey());
                classData.setPlaceDataSet(null);
                isOk = (classData = aircraftClassRepository.save(classData)) != null;
                logger.info("priem " + classData.toString());
            } else {
                classData = classDataList.get(0);
            }
            placeInfo.setCapacity(pair.getValue());
            placeInfo.setAirClass(classData);
            placeInfo.setAircraft(aircraft);
            isOk = aircraftPlaceRepository.save(placeInfo) != null;
        }
        return isOk;
    }


    /*
    иначе эта херь не работает. не понял вообще прикола в ЖПА.
    лишний геморой.
     */
    @Transactional
    public boolean insert(Aircraft aircraft) {
        AircraftClassData classData;
        List<AircraftClassData> classDataList;
        HashSet<AircraftPlaceInfo> places = new HashSet<>();
        AircraftPlaceInfo placeInfo;
        Aircraft emptyAircraft = new Aircraft(aircraft.getName());
        if (aircraftRepository.save(emptyAircraft) == null)
            return false;
        for (AircraftPlaceInfo currentPlaceInfo : aircraft.getPlaceInfoList()) {
            classDataList = aircraftClassRepository.findByName(currentPlaceInfo.getAirClass().getName());
            if (classDataList.isEmpty()) {
                classData = new AircraftClassData();
                classData.setName(currentPlaceInfo.getAirClass().getName());
                classData.setPlaceDataSet(null);
                if ( (classData = aircraftClassRepository.save(classData)) == null )
                    return false;
            } else {
                classData = classDataList.get(0);
            }
            placeInfo = new AircraftPlaceInfo(emptyAircraft,classData, currentPlaceInfo.getCapacity());
            places.add(placeInfo);
            return aircraftPlaceRepository.save(placeInfo) != null;
        }
        return true;
    }

    public boolean insert2(Aircraft aircraft) {
            return aircraftRepository.save(aircraft) != null;
    }

    public List<Aircraft> get(String name) {
        return aircraftRepository.findByName(name);
    }

    public List<Aircraft> getAll() {
        return aircraftRepository.findAll();
    }

    public List<AircraftClassData> getAllClasses() {
        return aircraftClassRepository.findAll();
    }

    public Aircraft get(Integer id) {
        return aircraftRepository.findOne(id);
    }

          /* List<AircraftPlaceInfo> placeInfoList = aircraft.getPlaceInfoList();
        for (int i = 0; i < placeInfoList.size(); i++) {
            if (placeInfoList.get(i).getId() == null) {
                if (placeInfoList.get(i).getAirClass().getId() == null) {
                    List <AircraftClassData> classDataList = aircraftClassRepository.findByName(placeInfoList.get(i).getAirClass().getName());
                    if (classDataList.isEmpty()) {
                        aircraftClassRepository.save(placeInfoList.get(i).getAirClass());
                    } else {
                        placeInfoList.set(i,placeInfoList.get(i));
                    }
                }
                AircraftPlaceInfo placeInfo = new AircraftPlaceInfo(placeInfoList.get(i).getAirClass(), placeInfoList.get(i).getCapacity());
                placeInfo.setAircraft(aircraft);
                aircraftPlaceRepository.save(placeInfo);
            }
        }*/

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
    public boolean delete (Aircraft aircraft) {
        Aircraft old = aircraftRepository.findOne(aircraft.getId());
        if (old == null)
            return false;
        aircraftRepository.delete(old.getId());
        return true;
    }

}
