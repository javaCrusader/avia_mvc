package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@ControllerAdvice
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    UserService userService;


    @ModelAttribute("userBalance")
    public Integer currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            logger.info(authentication.getName());
            return userService.getByName(authentication.getName()).getBalance();
        }
        return null;
    }

}
