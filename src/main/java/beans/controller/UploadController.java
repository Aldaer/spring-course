package beans.controller;

import beans.daos.UserInfoDAO;
import beans.models.Auditorium;
import beans.models.Event;
import beans.models.User;
import beans.models.UserInfo;
import beans.services.AuditoriumService;
import beans.services.EventService;
import beans.services.UserService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class UploadController {
    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Autowired
    @Qualifier("eventServiceImpl")
    EventService eventService;

    @Autowired
    AuditoriumService auditoriumService;

    @Autowired
    UserInfoDAO userInfoDAO;

    private final ObjectReader userReader;
    private final ObjectReader eventReader;

    public UploadController() {
        ObjectMapper omap = new ObjectMapper();
        omap.registerModule(new SimpleModule().addDeserializer(Auditorium.class, new AuditoriumDeserializer()));
        userReader = omap.readerFor(User[].class);
        eventReader = omap.readerFor(Event[].class);
    }

    @RequestMapping(path = {"/upload"}, method = POST)
    @Transactional
    public String upload(@RequestParam MultipartFile events, @RequestParam MultipartFile users) throws IOException {
        if (!users.isEmpty()) {
            addUsers(users.getBytes());
        }
        if (!events.isEmpty()) {
            addEvents(events.getBytes());
        }
        return "redirect:/";
    }

    private void addUsers(byte[] usersJson) throws IOException {
        User[] users = userReader.readValue(usersJson);
        Arrays.stream(users)
                .map(userService::register)
                .map(user -> new UserInfo(user, "123"))
        .forEach(userInfoDAO::registerUserInfo);
    }

    private void addEvents(byte[] eventsJson) throws IOException {
        Event[] events = eventReader.readValue(eventsJson);
        Arrays.stream(events).forEach(eventService::create);
    }

    class AuditoriumDeserializer extends JsonDeserializer<Auditorium> {
        @Override
        public Auditorium deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String name = jsonParser.readValueAs(String.class);
            return auditoriumService.getByName(name);
        }
    }
}

