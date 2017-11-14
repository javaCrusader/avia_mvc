package controller;

import model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.AircraftService;
import service.CrewService;
import service.FlightService;

import java.util.*;

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
    public String home(Model model) {
        model.addAttribute("flightList", flightService.getAll());
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

    @RequestMapping(value = "/newFlight", method = RequestMethod.GET)
    public String retrieveFlight(Model model, @RequestParam(value = "cmd", required = true) String cmd,
                               @RequestParam(value = "idFlight", required = false) Integer idFlight) {
        logger.info("newFlight GET");
        logger.info("newFlight GET id newFlight" + idFlight);
        Flight flight = null;

        //закидываем все роли команды в view.
        List<CompanyRole> functionList = crewService.getAllCompanyRoles();
        Map<String, List<CrewMember>> peopleMap = new HashMap<>();
        for (CompanyRole function : functionList) {
            peopleMap.put(function.getName(),crewService.getAllFreeByFunction(function.getName()));
        }
        model.addAttribute("peopleMap",peopleMap);
        model.addAttribute("cityList", flightService.getAllCities());
        model.addAttribute("aircraftList", aircraftService.getAllFree());

        if (cmd.equals("create")) {
            flight = new Flight();
            flight.setAircraft(new Aircraft());
            flight.setStartCity(new City());
            flight.setEndCity(new City());
            flight.setCrewMemberList(new ArrayList<CrewMember>(functionList.size()));
            model.addAttribute("cmd", "create");
        }
        if (cmd.equals("edit")) {
            flight = flightService.get(idFlight);
            model.addAttribute("cmd", "edit");
        }
        model.addAttribute("flight", flight);
        /*model.addAttribute("crewPilotList", crewService.getAllFreeByFunction("FUNC_PILOT"));
        model.addAttribute("crewSecondPilotList", crewService.getAllFreeByFunction("FUNC_SECOND_PILOT"));
        model.addAttribute("crewStewardList", crewService.getAllFreeByFunction("FUNC_STEWARD"));
        model.addAttribute("crewEngineerList", crewService.getAllFreeByFunction("FUNC_ENGINEER"));*/
        return "flight/newFlight";
    }

    @RequestMapping(value = "/newFlight", method = RequestMethod.POST)
    public String commitChanges(@ModelAttribute Flight flight, @RequestParam(value = "cmd", required = true) String cmd,
                                @RequestParam(value = "submit", required = true) String submit) {

        if (submit.equals("cancel"))
            return "redirect:/flights";
        /*по самолету приходит только ид, нужно связать с настоящим обьектом*/
        if (cmd.equals("create")) {
            Aircraft aircraft = aircraftService.get(flight.getAircraft().getId());
            flight.setAircraft(aircraft);
            aircraft.setFlight(flight);
            //для записи в БД заполняем полностью обьект
            flight.getCrewMemberList().stream().forEach(crewMember -> {
                //CrewMember dbMember = crewService.get(crewMember.getId());
                crewMember.setFlight(flight);
                //crewMember.setName(dbMember.getName());
            });
        }
        if (flightService.insert(flight))
            resultMessage = cmd.equals("create") ? "create flight ok" : "update flight ok";
        else
            resultMessage = cmd.equals("create") ? "create flight error" : "update flight error";
        return "redirect:/flights";
    }


}
