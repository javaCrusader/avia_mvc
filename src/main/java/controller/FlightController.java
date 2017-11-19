package controller;

import model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import result.search.SearchParam;
import service.AircraftService;
import service.CrewService;
import service.FlightService;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class FlightController {

    Logger logger = LoggerFactory.getLogger(AircraftController.class);

    @Autowired
    private FlightService flightService;

    @Autowired
    private AircraftService aircraftService;

    @Autowired
    private CrewService crewService;


    private String resultMessage;

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    public String home(@ModelAttribute SearchParam searchParam, @RequestParam(value = "cmd", required = false) String cmd,
                       Model model) {
        if (cmd != null && cmd.equals("search")) {
            model.addAttribute("flightList", flightService.getAll());

        } else
            model.addAttribute("flightList", flightService.getAll());
        model.addAttribute("searchParam", new SearchParam());
        model.addAttribute("message", resultMessage);
        resultMessage = null;
        return "flight/flights";
    }

    @RequestMapping(value = "/flights", method = RequestMethod.POST)
    public String homePost(@RequestParam(value = "idFlight", required = true) Integer id) {
        if (flightService.delete(id))
            resultMessage = "delete ok";
        else
            resultMessage = "delete fail";
        return "redirect:/flights";
    }

    @ModelAttribute(value = "countryMap")
    private Map<String, List<City>> getCountryMap() {
        List<Country> countryList = flightService.getAllCountries();
        Map<String, List<City>> countryMap = new HashMap<>();
        for (Country country : countryList) {
            countryMap.put(country.getName(), flightService.getCitiesByCountry(country.getName()));
        }
        return countryMap;
    }

    @RequestMapping(value = "/newFlight", method = RequestMethod.GET)
    public String retrieveFlight(Model model, @RequestParam(value = "cmd", required = true) String cmd,
                                 @RequestParam(value = "idFlight", required = false) Integer idFlight) {
        Flight flight = null;

        //закидываем все роли команды в view. ORDER by ID
        List<CompanyRole> functionList = crewService.getAllCompanyRoles(); //sorted by company role id
        LinkedHashMap<String, List<CrewMember>> peopleMap = new LinkedHashMap<>(); //it must be sorted too for editing
        for (CompanyRole function : functionList) {
            peopleMap.put(function.getName(), crewService.getAllFreeByFunction(function.getName()));
        }
        model.addAttribute("peopleMap", peopleMap);


        model.addAttribute("aircraftList", aircraftService.getAllFree());

        if (cmd.equals("create")) {
            flight = new Flight();
            flight.setAircraft(new Aircraft());
            flight.setStartCity(new City());
            flight.setEndCity(new City());
            flight.setCrewMemberList(new ArrayList<>(functionList.size()));
            model.addAttribute("cmd", "create");
        }
        if (cmd.equals("edit")) {
            flight = flightService.get(idFlight);
            List<CrewMember> currentCrew = crewService.getAllByFlight(idFlight); //sorted by company role id
            model.addAttribute("currentCrew", currentCrew);
            model.addAttribute("cmd", "edit");
        }
        model.addAttribute("flight", flight);
        return "flight/newFlight";
    }

    /**
     * все отсортировано, что бы можно было работать по индексам.
     *
     * @return
     */
    @RequestMapping(value = "/newFlight", method = RequestMethod.POST)
    public String commitChanges(@RequestParam(value = "prevAircraftId", required = false) Integer prevAircraftId,
                                @ModelAttribute Flight flight, @RequestParam(value = "cmd", required = true) String cmd) {

        /*по самолету приходит только ид, нужно связать с настоящим обьектом*/
        Aircraft aircraft = null;
        if (cmd.equals("create")) {
            aircraft = aircraftService.get(flight.getAircraft().getId());
            flight.setAircraft(aircraft);
            aircraft.setFlight(flight);
            //для записи в БД заполняем полностью обьект
            flight.setCrewMemberList(flight.getCrewMemberList().stream().
                    map(crewMember -> crewService.get(crewMember.getId()).setFlight(flight)).collect(Collectors.toList()));
        }
        if (cmd.equals("edit")) {
            if (prevAircraftId != flight.getAircraft().getId())
                aircraftService.insert(aircraftService.get(prevAircraftId).setFlight(null));
            Flight currFlight = flightService.get(flight.getId());
            aircraft = aircraftService.get(flight.getAircraft().getId());
            List<CrewMember> currentCrew = currFlight.getCrewMemberList();
            int i = 0;
            for (CrewMember member : flight.getCrewMemberList()) {
                if (member.getId() != null) {
                    currentCrew.set(i, crewService.get(member.getId()));
                }
                i++;
            }
           /* currFlight.setName(flight.getName());
            currFlight.setStart(flight.getStart());
            currFlight.setEnd(flight.getEnd());
            currFlight.setDone(flight.isDone());*/
            flight.setAircraft(aircraft);
            flight.setCrewMemberList(currentCrew);
            flight.setTicketList(currFlight.getTicketList());
            aircraft.setFlight(flight);

            // currFlight.setCrewMemberList(currentCrew);
        }
        if (aircraftService.insert(aircraft))
            resultMessage = cmd.equals("create") ? "create flight ok" : "update flight ok";
        else
            resultMessage = cmd.equals("create") ? "create flight error" : "update flight error";
        return "redirect:/flights";
    }


}
