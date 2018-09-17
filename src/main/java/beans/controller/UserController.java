package beans.controller;

import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller("/user")
public class UserController {
    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @RequestMapping(method = GET)
    public String getUserInfo(@RequestParam String email, Model model) {
        model.addAttribute("user", userService.getUserByEmail(email));
        return "users.html";
    }
}
