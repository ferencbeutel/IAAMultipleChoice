package de.nordakademie.multiplechoice.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Ferenc on 18.10.2016.
 */
@Service(value = "MailService")
@Getter
@Setter
@NoArgsConstructor
public class MailService {

    private String host;
    private String port;
    private boolean startTls;
    private boolean auth;
    private String user;
    private String password;

    public void sendMail(String to, String subject, String text) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.starttls.enable", startTls);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.ssl.trust", host);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            System.out.println("done sending");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
