package controller;

import model.Aircraft;
import model.AircraftClassData;
import model.AircraftPlaceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.AircraftService;

import javax.validation.Valid;
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


    /**
     * @param model
     * заполняем данные о допустпных классах и создаем объект самолета.
     * @return
     */
    //@SessionAttributes({ "aircraft"})  hz.
    @RequestMapping(value = "/newAircraft", method = RequestMethod.GET)
    public String createAircraft(Model model) {
        logger.info("newAircraft GET");
        Aircraft aircraft = new Aircraft();
        List<AircraftClassData> classDataList = aircraftService.getAllClasses();
        List<AircraftPlaceInfo> airPlaceList = new ArrayList<>();
        AircraftPlaceInfo airPlace;
        for (int i = 0; i < classDataList.size(); i++) {
            airPlace = new AircraftPlaceInfo();
            airPlace.setAirClass(classDataList.get(i));
            airPlaceList.add(airPlace);
        }
        aircraft.setPlaceInfoList(airPlaceList);
        model.addAttribute("aircraft", aircraft);
        return "aircraft/newAircraft";
    }


    @RequestMapping(value = "/newAircraft", method = RequestMethod.POST)
    public String saveCreated(@ModelAttribute Aircraft aircraft, @RequestParam(value = "submit", required = true) String cmd,
                              BindingResult bindingResult) {
        //model.addAttribute("aircraft", new Aircraft());
        logger.info("pizdec" +aircraft.getName());
        logger.info("binding errors? "+bindingResult.hasErrors());
        for (AircraftPlaceInfo info : aircraft.getPlaceInfoList()) {
            logger.info("id " + info.getId());
            logger.info("capacity " + info.getCapacity());
            logger.info("class " + info.getAirClass().getName() + info.getAirClass().toString());
            info.setAircraft(aircraft);
        }
        aircraftService.insert2(aircraft);
        return "redirect:/aircrafts";
    }
}
