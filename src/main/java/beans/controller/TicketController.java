package beans.controller;

import beans.daos.BookingDAO;
import beans.models.Ticket;
import beans.models.User;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/tickets")
@Secured({"ROLE_BOOKING_MANAGER"})
public class TicketController {
    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Autowired
    BookingDAO bookingDAO;

    @RequestMapping(method = GET)
    @Transactional(readOnly = true)
    public String getTickets(@RequestParam String email, Model model) {
        User user = userService.getUserByEmail(email);
        List<Ticket> tickets = bookingDAO.getTickets(user);
        model.addAttribute("tickets", tickets);
        model.addAttribute("user", user);
        return "tickets.html";
    }
}
