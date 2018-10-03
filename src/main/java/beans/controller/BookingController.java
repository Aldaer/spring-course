package beans.controller;

import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;
import beans.services.BookingService;
import beans.services.EventService;
import beans.services.UserAccountService;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/book")
@Secured({"ROLE_BOOKING_MANAGER"})
public class BookingController {
    @Autowired
    UserAccountService userAccountService;

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Autowired
    @Qualifier("eventServiceImpl")
    EventService eventService;

    @Autowired
    @Qualifier("bookingServiceImpl")
    BookingService bookingService;

    @RequestMapping(method = RequestMethod.GET, params = "!seats")
    public String displayBookingWindow(@RequestParam long eventId, Model model) {
        model.addAttribute("event", eventById(eventId));

        return "book.html";
    }

    @RequestMapping(method = RequestMethod.GET, params = "seats")
    public String displaySeatsAndPrice(@RequestParam long eventId, @RequestParam String seats, Model model) {
        List<Integer> seatList = parseSeats(seats);
        String filteredSeatList = seatList.stream().map(Object::toString).collect(Collectors.joining(","));
        model.addAttribute("seats", filteredSeatList);

        Event event = eventById(eventId);
        double price = bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(), event.getDateTime(), seatList, getCurrentUser());
        model.addAttribute("price", price);

        return displayBookingWindow(eventId, model);
    }

    private List<Integer> parseSeats(String seats) {
        return Arrays.stream(seats.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    private Event eventById(long id)
    {
        return eventService.getAll().stream().filter(evt -> evt.getId() == id).findAny().orElseThrow(() -> new IllegalArgumentException("Event not found!"));
    }

    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    public String doBooking(@RequestParam long eventId, @RequestParam String seats) {
        List<Integer> seatList = parseSeats(seats);
        User user = getCurrentUser();
        Event event = eventById(eventId);

        bookingService.bookTicket(user, new Ticket(event, LocalDateTime.now(), seatList, user,
                bookingService.getTicketPrice(event.getName(),
                        event.getAuditorium().getName(),
                        event.getDateTime(), seatList,
                        user)));
        return "redirect:/tickets?email=" + user.getEmail();
    }

    @ModelAttribute("balance")
    @Transactional(readOnly = true)
    public double getCurrentBalance() {
        return userAccountService.getBalance(getCurrentUser());
    }

    @ModelAttribute("currentUser")
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getUserByEmail(userEmail);
    }

}
