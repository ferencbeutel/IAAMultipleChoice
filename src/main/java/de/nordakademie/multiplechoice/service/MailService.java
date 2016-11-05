package de.nordakademie.multiplechoice.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
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

    public void sendMail(final String to, final String subject, final String text) throws MessagingException {

        Properties properties = buildProperties();
        Session session = initSession(properties);

        Message message = buildMessage(session, to, subject, text);
        Transport.send(message);
    }

    private Properties buildProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.starttls.enable", startTls);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.ssl.trust", host);

        return props;
    }

    private Session initSession(final Properties properties) {
        Authenticator authenticator = new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        };
        return Session.getInstance(properties, authenticator);
    }

    private Message buildMessage(final Session session, final String to, final String subject, final String text) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(user));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(text, "utf-8", "html");

        return message;
    }
}
