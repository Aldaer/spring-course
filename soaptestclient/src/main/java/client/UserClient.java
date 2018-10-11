package client;

import model.GetUser;
import model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@SpringBootApplication
public class UserClient extends WebServiceGatewaySupport
{
    private User getUser(String email)
    {
        GetUser getUser = new GetUser();
        getUser.setEmail(email);

        return (User) getWebServiceTemplate()
            .marshalSendAndReceive("http://localhost:8080/ws/users", getUser);
    }

    @Bean
    CommandLineRunner lookUpUser()
    {
        return emails -> {
            for (final String email : emails)
            {
                User user = getUser(email);
                System.out.println("User name:" + user.getName());
                System.out.println("User email:" + user.getEmail());
            }

        };
    }

    public static void main(String[] args)
    {
        SpringApplication.run(UserClient.class);
    }
}
