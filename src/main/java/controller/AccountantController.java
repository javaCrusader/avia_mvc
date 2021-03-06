package controller;

import model.CrewMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import result.accountantResult.AccountantResultWrapper;
import result.accountantResult.SingleResult;
import service.CrewService;

@Controller
public class AccountantController {

    Logger logger = LoggerFactory.getLogger(CrewController.class);

    @Autowired
    private CrewService crewService;


    @RequestMapping(value = "/accountant", method = RequestMethod.GET)
    public String home(@RequestParam(value = "submit", required = false) String cmd, @ModelAttribute(value = "accResult") AccountantResultWrapper calcResult, Model model) {
        AccountantResultWrapper accResult = new AccountantResultWrapper();
        for (CrewMember member : crewService.getAll()) {
            SingleResult result = new SingleResult();
            result.setId(member.getId());
            result.setName(member.getName());
            result.setSalaryInHour(member.getSalaryInHour());
            result.setFuncName(member.getFunction().getName());
            accResult.getAccountantResultList().add(result);
        }
        model.addAttribute("accResult", accResult);

        if (cmd != null && cmd.equals("submit")) {
            AccountantResultWrapper filledResult = new AccountantResultWrapper();
            for (SingleResult result : calcResult.getAccountantResultList()) {
                result.calculate();
                if (result.getSalaryInPeriod() != null)
                    filledResult.getAccountantResultList().add(result);
            }
            model.addAttribute("filledResult", filledResult);
        }
        return "accountant/accountant";
    }

}
