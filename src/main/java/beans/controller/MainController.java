package beans.controller;

import beans.models.User;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class MainController {
    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @RequestMapping(path = {"/"}, method = GET)
    public String index() {
        return "index.html";
    }

    @ModelAttribute("user")
    public User getLoggedInUser(Principal principal) {
        String email = principal.getName();
        return userService.getUserByEmail(email);
    }
}
