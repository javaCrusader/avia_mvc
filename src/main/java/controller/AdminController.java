package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.RoleService;
import service.UserService;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private String resultMessage;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("userList", userService.getAll()); //crew member function name exception
        model.addAttribute("fullRoleList", roleService.getAll());
        model.addAttribute("message", resultMessage);
        resultMessage = null;
        return "admin/adminPanel";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String homePost(@RequestParam(value = "idUser") Integer idUser, Model model) {
        if (userService.delete(idUser))
            resultMessage = "delete ok";
        else
            resultMessage = "delete fail. May be you want delete yourself?";
        return "redirect:/admin";
    }


    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public String newUser(@RequestParam(value = "cmd") String cmd, @RequestParam(value = "idUser", required = false) Integer idUser, Model model) {
        model.addAttribute("fullRoleList", roleService.getAll());
        if (cmd.equals("create")) {
            model.addAttribute("user", new User());
            model.addAttribute("cmd", "create");
        }
        if (cmd.equals("edit")) {
            model.addAttribute("user", userService.get(idUser));
            model.addAttribute("cmd", "edit");
        }

        model.addAttribute("message", resultMessage);
        resultMessage = null;
        return "admin/newUser";
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String commitNewUser(@RequestParam(value = "cmd") String cmd, @ModelAttribute User user, Model model) {
        if (cmd.equals("create")) {
            User userExists = userService.getByName(user.getName());
            if (userExists != null) {
                resultMessage = "user exists";
                return "redirect:/admin";
            }
            userService.insertPwdEncode(user);
        }
        if (cmd.equals("edit")) {
            User userExists = userService.getByName(user.getName());
            if (userExists != null) {
                user.setIssueList(userExists.getIssueList());
                user.setTicketsList(userExists.getTicketsList());
                user.setPassword(userExists.getPassword());
                if (userService.saveCompleteObject(user))
                    resultMessage = "User has been edited successfully";
                else {
                    resultMessage = "register user fail";
                    return "redirect:/main";
                }
            }

        }
        model.addAttribute("roleList", roleService.getAll());
        return "redirect:/admin";
    }
}
