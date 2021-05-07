package com.revature.service;

import com.revature.models.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    //address of backend to use for hitting the confirm user method.
    private final String APP_URL       = System.getenv("APP_URL");
    private final String EMAIL_ADDRESS = System.getenv("email_address");

    @Autowired
    public EmailService(final JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    private JavaMailSender getJavaMailSender() {
        return javaMailSender;
    }

    @Async
    public void sendEmail(final User user) throws MessagingException {
        final MimeMessage mailMessage = getJavaMailSender().createMimeMessage();
        mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
        mailMessage.setSubject("Complete Registration For Asteria-Alpha.");
        mailMessage.setFrom(EMAIL_ADDRESS);
        final String confirm_url = String.format("%s/confirmation/%s", APP_URL, user.getUsername());
        final String html        = String.format(
                "<div style=\"text-align:center;border:3px solid black;margin-left:25rem;margin-right:25rem;margin-top:25rem\">" +
                        "<h3>Thank you for signing up for Asteria-Alpha, %s!</h3>" +
                        "<p>Click the following link to confirm your account: " +
                        "<a href=\"%s\">Confirm Your Account</a>" +
                        "</p" +
                        "</div>", user.getUsername(), confirm_url);

        mailMessage.setContent(html, "text/html; charset=utf-8");

        javaMailSender.send(mailMessage);
    }

}
