package beans.controller;

import beans.models.Auditorium;
import beans.services.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller("/rooms")
public class AuditoriumController {
    @Autowired
    AuditoriumService auditoriumService;


    @RequestMapping(method = GET)
    public String getAuditoriumNames(@ModelAttribute("roomNames") ArrayList<String> roomNames) {
        List<String> rooms = auditoriumService.getAuditoriums().stream()
                .map(Auditorium::getName)
                .collect(Collectors.toList());
        roomNames.addAll(rooms);
        return "rooms.html";
    }

    @RequestMapping(params = {"roomName"}, method = GET)
    public String getAuditorium(@RequestParam String roomName, Model model) {
        Auditorium auditorium = auditoriumService.getByName(roomName);
        model.addAttribute("roomInfo", auditorium);
        return "roominfo.html";
    }
}
