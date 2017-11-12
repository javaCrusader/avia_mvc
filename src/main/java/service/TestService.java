package service;

import model.Issue;
import model.Role;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AircraftClassRepository;
import repository.AircraftPlaceRepository;
import repository.AircraftRepository;
import repository.RoleRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class TestService {

    Logger logger = LoggerFactory.getLogger(TestService.class);

    public void timerActivity() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                logger.info("hello world");
            }
        }, 0, 6000);
    }

    @Autowired
    AircraftService aircraftService;

    @Autowired
    CrewService crewService;

    @Autowired
    AircraftClassRepository aircraftClassRepository;

    @Autowired
    AircraftRepository aircraftRepository;

    @Autowired
    AircraftPlaceRepository aircraftPlaceRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    public void dbActivity() {
        System.out.println("preved");
       /* Aircraft yak= new Aircraft("yak-42");
        Map<String,Integer> placeDetails = new HashMap<>();
        HashSet<AircraftPlaceInfo> places = new HashSet<>();
        places.add(new AircraftPlaceInfo(new AircraftClassData("super luxe"),23));
        places.add(new AircraftPlaceInfo(new AircraftClassData("eco"),54));
        yak.setPlaceInfoList(places);
        aircraftService.insert(yak);*/
        /*placeDetails.put("super luxe" , 23);
        placeDetails.put("econom" , 54);
        aircraftService.constructInsert(placeDetails,"boeing");*/

        /*Aircraft aircraft = aircraftService.get("samolet").get(0);
        for (AircraftPlaceInfo placeInfo : aircraft.getPlaceInfoList()) {
            logger.info(placeInfo.getAirClass().getName());
            logger.info(" " + placeInfo.getCapacity());
        }
        aircraft.setName("preved");
        logger.info("   " + aircraft.getPlaceInfoList().get(0).getId());
        aircraft.getPlaceInfoList().add(new AircraftPlaceInfo(aircraft, aircraftClassRepository.findByName("super puper").get(0),79));
        aircraftService.update(aircraft);
        aircraftService.delete(aircraft);*/

       /* User user = new User();
        user.setName("vasya");
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(1));
        userService.insert(user, roles);
        userService.addIssue(user, new Issue(user, "pizdec"));

*/











       /* AircraftClassData aircraftClassData1 = aircraftClassRepository.getOne(1);
        AircraftClassData aircraftClassData2 = aircraftClassRepository.getOne(3);

        List<AircraftPlaceInfo> aircraftPlaceInfos = new ArrayList<>(Arrays.asList(
                new AircraftPlaceInfo(aircraftClassData1, 555),
                new AircraftPlaceInfo(aircraftClassData2, 232))
        );
        Aircraft aircraft = new Aircraft("test32", aircraftPlaceInfos);

        //aircraftService.insert(aircraft);
        aircraftRepository.save(aircraft);*/
//        aircraftService.update(aircraft);
        /*crewService.constructInsert("BOB","10/10/2014","12/10/2014","topManager");
        CrewMember member = new CrewMember();
        member.setName("john");
        member.setFunction(new CompanyRole("developer"));
        try {
            member.setVacation(new CrewMemberVacation("11/10/2016","11/11/2016"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        crewService.insert(member);*/




/*
        HashSet<AircraftPlaceInfo> places = new HashSet<>();
        AircraftClassData classData;
        List<AircraftClassData> classDataList = aircraftClassRepository.findAll();
        if (classDataList.isEmpty()) {
            classData = new AircraftClassData();
            classData.setName("business");
            classData.setPlaceDataSet(null);
            classData = aircraftClassRepository.save(classData);
        } else {
            classData = classDataList.get(0);
        }

        AircraftPlaceInfo placeInfo = new AircraftPlaceInfo();
        placeInfo.setAirClass(classData);
        placeInfo.setCapacity(27);
        Aircraft aircraft = new Aircraft("newAi3_preved");
        places.add(placeInfo);
        aircraft.setPlaceInfoList(places);
        aircraftRepository.save(aircraft);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        try {
            CrewMemberVacation vac = new CrewMemberVacation(sdf.parse("31/08/2015"), sdf.parse("31/08/2017"));
            CrewMember member = new CrewMember("ivan", companyRoleRepository.findOne(1), vac);
            crewRepository.save(member);
        } catch (ParseException e) {
            logger.info("date parse fail");
            System.exit(-1);
        }
*/

    }
}
