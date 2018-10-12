package beans.soap;


import beans.models.GetUser;
import beans.models.User;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {

    public static final String NAMESPACE_URI = "http://epam.com/spring-advanced-41";
    private final UserService userService;

    @Autowired
    public UserEndpoint(@Qualifier("userServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUser")
    @ResponsePayload
    public User getUser(@RequestPayload GetUser getUser) {
        return userService.getUserByEmail(getUser.getEmail());
    }

}
