package controller;

import model.Aircraft;
import model.AircraftClassData;
import model.AircraftPlaceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.AircraftService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AircraftController {

    Logger logger = LoggerFactory.getLogger(AircraftController.class);

    @Autowired
    private AircraftService aircraftService;

    private String resultMessage;

    @RequestMapping(value = "/aircrafts", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("aircraftList", aircraftService.getAll());
        model.addAttribute("message", resultMessage);
        resultMessage = null;
        return "aircraft/aircrafts";
    }

    @RequestMapping(value = "/aircrafts", method = RequestMethod.POST)
    public String homePost(@RequestParam(value = "idAircraft", required = false) Integer id, Model model) {
        if (aircraftService.delete(id))
            resultMessage = "delete ok";
        else
            resultMessage = "delete fail";
        return "redirect:/aircrafts";
    }


    /**
     * @param model заполняем данные о допустпных классах и создаем объект самолета.
     * @return
     */
    //@SessionAttributes({ "aircraft"})  hz.
    @RequestMapping(value = "/newAircraft", method = RequestMethod.GET)
    public String retrieveAircraft(Model model, @RequestParam(value = "cmd", required = true) String cmd,
                                   @RequestParam(value = "idAircraft", required = false) Integer idAircraft) {
        Aircraft aircraft = null;
        if (cmd.equals("create")) {
            aircraft = new Aircraft();
            List<AircraftClassData> classDataList = aircraftService.getAllClasses();
            List<AircraftPlaceInfo> airPlaceList = new ArrayList<>();
            AircraftPlaceInfo airPlace;
            for (AircraftClassData classData : classDataList) {
                airPlace = new AircraftPlaceInfo();
                airPlace.setAirClass(classData);
                airPlaceList.add(airPlace);

            }
            aircraft.setPlaceInfoList(airPlaceList);
            model.addAttribute("cmd", "create");
        }
        if (cmd.equals("edit")) {
            aircraft = aircraftService.get(idAircraft);
            model.addAttribute("cmd", "edit");
        }
        model.addAttribute("aircraft", aircraft);

        return "aircraft/newAircraft";
    }


    @RequestMapping(value = "/newAircraft", method = RequestMethod.POST)
    public String commitChanges(@ModelAttribute Aircraft aircraft, @RequestParam(value = "cmd", required = true) String cmd) {
        if (cmd.equals("create")) {
            for (AircraftPlaceInfo info : aircraft.getPlaceInfoList())
                info.setAircraft(aircraft);
            if (aircraftService.insert(aircraft))
                resultMessage = "create aircraft ok";
            else
                resultMessage = "create aircraft error";
        }
        if (cmd.equals("edit")) {
            if (aircraftService.update(aircraft))
                resultMessage = "update aircraft ok";
            else
                resultMessage = "update aircraft error";
        }
        return "redirect:/aircrafts";

    }
}
