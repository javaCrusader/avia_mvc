package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.UserService;

@Controller
public class IssueController {

    Logger logger = LoggerFactory.getLogger(CrewController.class);

    @Autowired
    private UserService userService;

    private String resultMessage;

    @RequestMapping(value = "/issue", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("issueList", userService.getAllIssue());
        model.addAttribute("message", resultMessage);
        resultMessage = null;
        return "issue/issue";
    }

}
