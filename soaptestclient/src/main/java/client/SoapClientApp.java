package client;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SoapClientApp
{
    private final UserClient userClient;

    @Autowired
    public SoapClientApp(final UserClient userClient)
    {
        this.userClient = userClient;
    }

    public void run(final String... emails)
    {
        for (String email : emails)
        {
            User user = userClient.getUser(email);
            System.out.println("User name:" + user.getName());
            System.out.println("User email:" + user.getEmail());
        }
    }

    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext("client");

        SoapClientApp app = context.getBean(SoapClientApp.class);
        if (args.length == 0)
        {
            System.out.println("Specify user emails in command line");
            args = new String[]{"artem_lodygin@epam.com"};
        }

        app.run(args);
    }
}
