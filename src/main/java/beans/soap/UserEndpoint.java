package beans.soap;


import beans.models.User;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class UserEndpoint {

    private final UserService userService;

    @Autowired
    public UserEndpoint(@Qualifier("userServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @PayloadRoot(localPart = "getUser")
    @ResponsePayload
    public User getUser(@RequestPayload String email) {
        return userService.getUserByEmail(email);
    }

    @PayloadRoot(localPart = "getUsers")
    @ResponsePayload
    public List<User> getUsers() {
        return userService.getUsersByName("");
    }

}
