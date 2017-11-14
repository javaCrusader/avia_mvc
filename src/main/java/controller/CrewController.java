package controller;

import model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import service.AircraftService;
import service.CrewService;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CrewController {

    Logger logger = LoggerFactory.getLogger(CrewController.class);

    @Autowired
    private CrewService crewService;

    private String resultMessage;

    @RequestMapping(value = "/crew", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("crewList", crewService.getAll());
        model.addAttribute("message", resultMessage);
        resultMessage = null;
        return "crew/crew";
    }

    @RequestMapping(value = "/crew", method = RequestMethod.POST)
    public String homePost(@RequestParam(value = "idMember", required = false) Integer id,Model model) {
        if (crewService.delete(id))
            resultMessage = "delete ok";
        else
            resultMessage = "delete fail";
        return "redirect:/crew";
    }


    @RequestMapping(value = "/newCrewMember", method = RequestMethod.GET)
    public String retrieveCrew(Model model, @RequestParam(value = "cmd", required = true) String cmd,
                                   @RequestParam(value = "idCrewMember", required = false) Integer idCrewMember) {
        logger.info("newCrewmember GET");
        logger.info("newCrewmember GET id member" + idCrewMember);
        CrewMember member = null;
        if (cmd.equals("create")) {
            member = new CrewMember();
            member.setFunction(new CompanyRole());
            member.setVacation(new CrewMemberVacation());
            model.addAttribute("cmd", "create");
        }
        if (cmd.equals("edit")) {
            member = crewService.get(idCrewMember);
            model.addAttribute("cmd", "edit");
        }
        model.addAttribute("member", member);
        model.addAttribute("companyRoleList", crewService.getAllCompanyRoles());
        return "crew/newCrewMember";
    }

    @RequestMapping(value = "/newCrewMember", method = RequestMethod.POST)
    public String commitChanges(@ModelAttribute CrewMember member, @RequestParam(value = "cmd", required = true) String cmd,
                                @RequestParam(value = "submit", required = true) String submit,
                                BindingResult bindingResult, Model model) {


        //member.getFunction().addCrewMember(member);
        if (submit.equals("cancel"))
            return "redirect:/crew";
        if (cmd.equals("create"))
            member.setFunction(crewService.getCompanyRole(member.getFunction().getId()));
        if (crewService.insert(member))
            resultMessage = cmd.equals("create") ? "create member ok" : "update member ok";
        else
            resultMessage = cmd.equals("create") ? "create member error" : "update member error";
        return "redirect:/crew";

    }
/*
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
        dataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String value) {
                try {
                    setValue(CrewMemberVacation.getDateFmt().parse(value));
                } catch (ParseException e) {
                    setValue(null);
                }
            }
        });

    }*/
}

