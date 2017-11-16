package controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ControllerAdvice("controller")
public class ErrorController {

    private static Logger logger = LoggerFactory.getLogger(ErrorController.class);

    {
        logger.info("ERROR CONTROLLER HERE");
    }

    @ExceptionHandler(Exception.class)
    public String exception(Exception throwable, Model model) {
        logger.error("Exception during execution of SpringSecurity application", throwable);
        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }

    @ExceptionHandler(IOException.class)
    public String handleException(HttpServletRequest request, Exception ex) {
        logger.info("exception:: URL=" + request.getRequestURL());
        return "error";
    }


}
