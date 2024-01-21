package ba.edu.ibu.eventbooking.core.api.mailsender;


import ba.edu.ibu.eventbooking.core.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MailSender {

    public String send(List<User> users, String message);
}