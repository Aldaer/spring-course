package beans.controller;

import beans.models.Event;
import beans.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/events")
public class EventController {
    @Autowired
    @Qualifier("eventServiceImpl")
    EventService eventService;

    @RequestMapping(method = GET)
    public String getEventList(@ModelAttribute("events") ArrayList<Event> events) {
        List<Event> eventList = eventService.getAll();
        events.addAll(eventList);
        return "events.html";
    }
}
