package beans.controller;

import beans.models.User;
import beans.services.UserAccountService;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Autowired
    UserAccountService userAccountService;

    @RequestMapping(method = GET)
    public String getUserInfo(@RequestParam String email, Model model) {
        User currentUser = userService.getUserByEmail(email);
        double balance = userAccountService.getBalance(currentUser);
        model.addAttribute("user", currentUser);
        model.addAttribute("balance", balance);
        return "users.html";
    }
}
