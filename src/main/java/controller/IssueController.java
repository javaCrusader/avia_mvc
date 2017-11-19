package controller;

import model.Issue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import result.accountantResult.issuesResult.IssueWrapper;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IssueController {

    Logger logger = LoggerFactory.getLogger(CrewController.class);

    @Autowired
    private UserService userService;

    private String resultMessage;


    @RequestMapping(value = "/issue", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("issueWrapper", new IssueWrapper().setIssueList(userService.getAllIssue()));
        model.addAttribute("message", resultMessage);
        resultMessage = null;
        return "issue/issue";
    }


    @RequestMapping(value = "/issue", method = RequestMethod.POST)
    public String homePost(@ModelAttribute IssueWrapper issueWrapper) {
        for (Issue issue : issueWrapper.getIssueList()) {
            if (!userService.saveIssue(userService.getIssue(issue.getId()).setClosed(issue.isClosed()))){
                resultMessage = "save issue failed";
                return "redirect:/main";
            }
        }
        resultMessage = "no such issue";
        return "redirect:/issue";
    }


}
