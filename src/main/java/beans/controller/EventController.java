package beans.controller;

import beans.models.Auditorium;
import beans.models.Event;
import beans.models.Rate;
import beans.services.AuditoriumService;
import beans.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/events")
public class EventController {
    @Autowired
    @Qualifier("eventServiceImpl")
    EventService eventService;

    @Autowired
    @Qualifier("auditoriumServiceImpl")
    AuditoriumService auditoriumService;

    @RequestMapping(method = GET)
    public String getEventList() {
        return "events.html";
    }

    @RequestMapping(method = POST)
    public String createEvent(@RequestParam String name,
                              @RequestParam
                              @DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm")
                                      LocalDateTime datetime,
                              @RequestParam Rate rate,
                              @RequestParam("room") String roomName) {
        double basePrice = 20.0;
        Auditorium auditorium = auditoriumService.getByName(roomName);
        Event newEvent = new Event(name, rate, basePrice, datetime, auditorium);
        eventService.create(newEvent);
        return "redirect:/events";
    }

    @ModelAttribute("rooms")
    public List<Auditorium> populateAuditoriums() {
        return auditoriumService.getAuditoriums();
    }

    @ModelAttribute("events")
    public List<Event> populateEvents() {
        return eventService.getAll();
    }
}
