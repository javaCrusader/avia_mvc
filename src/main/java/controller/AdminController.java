package controller;

import model.Hobby;
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

import java.util.ArrayList;
import java.util.List;

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
        List<Hobby> allhobby = new ArrayList<>();
        Hobby hobby = new Hobby();
        hobby.setId("1");
        hobby.setDescription("fdsfsdfsd");
        allhobby.add(hobby);
        model.addAttribute("allHobbies",allhobby);
        model.addAttribute("message", resultMessage);
        resultMessage = null;
        return "admin/adminPanel";
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public String newUser(@RequestParam (value = "cmd") String cmd, @RequestParam (value = "idUser") Integer idUser,Model model) {
        model.addAttribute("fullRoleList", roleService.getAll());
        if (cmd.equals("create")) {
            // userService.insertWithRoles()
            model.addAttribute("user",new User());
        }
        if (cmd.equals("edit")) {
            model.addAttribute("user",userService.get(idUser));
        }

        model.addAttribute("message", resultMessage);
        resultMessage = null;
        return "admin/newUser";
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String commitNewUser(@RequestParam (value = "cmd") String cmd, @ModelAttribute User user, Model model) {
        if (cmd.equals("create")) {
           // userService.insertWithRoles()
        }
        if (cmd.equals("edit")) {

        }
        model.addAttribute("roleList", roleService.getAll());
        return "admin/newUser";
    }
}
