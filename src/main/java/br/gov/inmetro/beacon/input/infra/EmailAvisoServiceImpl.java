package br.gov.inmetro.beacon.input.infra;

import br.gov.inmetro.beacon.input.BeaconInputApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Profile({"producao", "test"})
public class EmailAvisoServiceImpl implements IEmailAvisoService {

    private JavaMailSender mailSender;

    private Environment env;

    @Value("${beacon.mail.send-contacts}")
    private String emails;

    private HttpServletRequest request;


    @Autowired
    public EmailAvisoServiceImpl(JavaMailSender mailSender, HttpServletRequest request, Environment env) {
        this.mailSender = mailSender;
        this.request = request;
        this.env = env;
    }

    private static final Logger logger = LoggerFactory.getLogger(BeaconInputApplication.class);

    @Override
    @Async
    public void sendSimpleMessage(String subject, StringBuilder text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(env.getProperty("beacon.mail.sender"));
            message.setTo(emails);
            message.setSubject(subject);
            text.append(String.format("Email sent from: %s", getAppUrl(request)));
            mailSender.send(message);
        } catch (MailException exception) {
            logger.error(exception.getMessage());
        }
    }

    private String getAppUrl(HttpServletRequest request) {
        if (request.getServerPort() != 443){
            return request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        } else {
            return request.getServerName() + request.getContextPath();
        }
    }

}
