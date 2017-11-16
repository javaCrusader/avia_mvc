package controller;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.unbescape.html.HtmlEscape;
import security.SecurityService;
import service.FlightService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private FlightService flightService;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    private String resultMessage;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {
        return "user/login";
    }


    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("name", "anon");
        logger.info("DO_GET");
        model.addAttribute("flightList", flightService.getAll());
        model.addAttribute("message", resultMessage);
        resultMessage = null;
        return "main";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(Model model, @Valid User user, BindingResult bindingResult) {
        User userExists = userService.getByName(user.getName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with this username");
        }
        logger.info(model.toString());
        if (!bindingResult.hasErrors()) {
            if (userService.insert(user))
                resultMessage = "User has been registered successfully";
            else {
                resultMessage = "register user fail";
                return "redirect:/main";
            }
            securityService.autoLogin(user.getName(), user.getPassword());
        }
        return "redirect:/main";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        logger.info("DO_GET TRY TO REGISTER");
        model.addAttribute("user", new User());
        return "user/registration";
    }

    @RequestMapping(value = {"/userdata"}, method = RequestMethod.GET)
    public String userDataRetrieve(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            logger.info(authentication.getName());
            model.addAttribute("user", userService.getByName(authentication.getName()));
        }
        return "user/userdata";
    }

    @RequestMapping(value = {"/userdata"}, method = RequestMethod.POST)
    public String userDataCommit(@RequestParam(value = "submit", required = true) String cmd, User user, Model model) {
        logger.info("get " + user.getFirstName());
        logger.info("get command " + cmd);
        if (!cmd.equals("cancel")) {
            if (!userService.update(user))
                resultMessage = "fail to save user data";
            else
                resultMessage = "user data saved";
        }
        return "redirect:/main";
    }


    /**
     * Error page.
     */
    @RequestMapping("/error.html")
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("<ul>");
        while (throwable != null) {
            errorMessage.append("<li>").append(HtmlEscape.escapeHtml5(throwable.getMessage())).append("</li>");
            throwable = throwable.getCause();
        }
        errorMessage.append("</ul>");
        model.addAttribute("errorMessage", errorMessage.toString());
        return "error";
    }

    /**
     * Error page.
     */
    @RequestMapping("/403.html")
    public String forbidden() {
        return "403";
    }


}