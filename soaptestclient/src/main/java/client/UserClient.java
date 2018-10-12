package client;

import model.GetUser;
import model.User;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class UserClient extends WebServiceGatewaySupport
{
    public User getUser(String email)
    {
        GetUser getUser = new GetUser();
        getUser.setEmail(email);

        return (User) getWebServiceTemplate().marshalSendAndReceive(getUser);
    }

}
