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
 * This class is used to send mail
 *
 * @author Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
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

  /**
   * This method is used to send a mail
   *
   * @param to      Recipient of mail
   * @param subject subject line
   * @param text    content of mail
   *
   * @throws MessagingException
   */
  public void sendMail(final String to, final String subject, final String text) throws MessagingException {

    Properties properties = buildProperties();
    Session session = initSession(properties);

    Message message = buildMessage(session, to, subject, text);
    Transport.send(message);
  }

  /**
   * This method is used to create mail properties
   *
   * @return properties
   */
  private Properties buildProperties() {
    Properties props = new Properties();
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.starttls.enable", startTls);
    props.put("mail.smtp.auth", auth);
    props.put("mail.smtp.port", port);
    props.put("mail.smtp.ssl.trust", host);

    return props;
  }

  /**
   * This method initializes a session from a user in given properties
   *
   * @param properties of session
   *
   * @return initialized session
   */
  private Session initSession(final Properties properties) {
    Authenticator authenticator = new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
      }
    };
    return Session.getInstance(properties, authenticator);
  }

  /**
   * This method is used to create a message, that can be send
   *
   * @param session
   * @param to      recipient
   * @param subject line
   * @param text    of mail
   *
   * @return created message
   *
   * @throws MessagingException
   */
  private Message buildMessage(final Session session, final String to, final String subject, final String text)
      throws MessagingException {
    MimeMessage message = new MimeMessage(session);
    message.setFrom(new InternetAddress(user));
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
    message.setSubject(subject);
    message.setText(text, "utf-8", "html");

    return message;
  }
}
