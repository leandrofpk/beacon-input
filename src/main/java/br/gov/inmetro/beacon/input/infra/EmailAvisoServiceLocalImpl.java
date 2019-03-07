package br.gov.inmetro.beacon.input.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Profile("default")
public class EmailAvisoServiceLocalImpl implements IEmailAvisoService {


    private Environment env;

    @Value("${beacon.mail.send-contacts}")
    private String emails;

    private HttpServletRequest request;


    @Autowired
    public EmailAvisoServiceLocalImpl(Environment env) {
        this.env = env;
    }

    @Override
    @Async
    public void sendSimpleMessage(String subject, StringBuilder text) {
        text.append((String.format("Email sent from: %s", "localhost")));
        System.out.println(text);
    }


}
