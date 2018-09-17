package beans.controller;

import beans.models.Ticket;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class TicketController {
    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @RequestMapping(value = "/tickets", method = GET)
    public String getTickets(@RequestParam String email, @ModelAttribute("tickets") ArrayList<Ticket> tickets) {
        tickets.addAll(userService.getBookedTickets());
        return "tickets.html";
    }
}
