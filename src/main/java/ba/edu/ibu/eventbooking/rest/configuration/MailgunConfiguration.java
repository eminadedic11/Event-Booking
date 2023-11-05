package ba.edu.ibu.eventbooking.rest.configuration;


import ba.edu.ibu.eventbooking.api.impl.mailsender.MailgunSender;
import core.api.mailsender.MailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class MailgunConfiguration {
    @Value("${spring.mailgun.from-email}")
    private String fromEmail;
    @Value("${spring.mailgun.username}")
    private String username;
    @Value("${spring.mailgun.password}")
    private String password;

    @Value("${spring.mailgun.domain}")
    private String domain;

    @Bean
    public String fromEmail() {
        return fromEmail;
    }

    @Bean
    public MailSender mailgunMailSender(RestTemplate restTemplate, String fromEmail) {
        return new MailgunSender(restTemplate, fromEmail);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.uriTemplateHandler(new DefaultUriBuilderFactory(domain)).basicAuthentication(username, password).build();
    }
}