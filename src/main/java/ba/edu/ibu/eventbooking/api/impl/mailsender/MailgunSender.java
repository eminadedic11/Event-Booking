package ba.edu.ibu.eventbooking.api.impl.mailsender;
import ba.edu.ibu.eventbooking.core.model.User;
import ba.edu.ibu.eventbooking.core.api.mailsender.MailSender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;


//@ConditionalOnProperty(name = "mailsender.default", havingValue = "mailgun")
@Component
@Qualifier("mailgunSender")
@Primary
public class MailgunSender implements MailSender {

    private final RestTemplate restTemplate;

    @Value("${email.mailgun.from-email}")
    private String fromEmail;

    public MailgunSender(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String send(List<User> users, String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("from", fromEmail);
        for (User user : users) {
            map.add("to", user.getEmail());
        }
        map.add("subject", "Test message");
        map.add("text", message);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        return restTemplate.postForEntity("/messages", request, String.class).getBody();
    }
}
