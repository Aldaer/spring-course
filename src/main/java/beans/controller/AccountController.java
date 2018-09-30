package beans.controller;

import beans.models.User;
import beans.services.UserAccountService;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Autowired
    UserAccountService userAccountService;

    @RequestMapping("/deposit")
    public String deposit(@RequestParam String email, @RequestParam double amount) {
        User user = userService.getUserByEmail(email);
        System.out.printf("Crediting account: %s, amount: %5.2f\n", user.getName(), amount);
        userAccountService.creditAccount(user, amount);
        return "redirect:/user?email=" + email;
    }
}
