package de.nordakademie.multiplechoice.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.multiplechoice.model.User;
import de.nordakademie.multiplechoice.service.MailService;
import de.nordakademie.multiplechoice.service.UUIDService;
import de.nordakademie.multiplechoice.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Ferenc on 19.10.2016.
 */
public class RegistrationConfirmationAction extends ActionSupport {

    private static final String SUBJECT_LINE = "Your registration at the Nordakademie Seminartool";
    private static final String MAIL_TEXT_BEGINNING = "<p>Thank you for registring at the Nordakademie Seminartool. Please open the following link to complete your registration</p>";
    private static final String MAIL_TEXT_ENDING = "<p>If you cant open the link directly, try to copy and paste it directly into your browser.</p>";
    private static final String MAIL_TEXT_GREETING = "<p>Yours sincerely,</p>";
    private static final String MAIL_TEXT_SIGNATURE = "<p>The Nordakademie Seminartool</p>";

    @Autowired
    MailService mailService;

    @Getter
    @Setter
    private User user;

    public String sendConfirmationMail() {
        HttpServletRequest request = ServletActionContext.getRequest();
        final String protocol = request.getScheme();
        final String host = request.getLocalAddr() + ":" + request.getLocalPort() + "/";
        final String action = "acceptRegistration";
        final String uuidParameterName = "regCode";
        final String link = protocol + "://" + host + action + "?" + uuidParameterName + "=" + user.getRegToken();
        final String htmlLink = "<a href=" + link + ">" + link + "</a>";

        try {
            mailService.sendMail(user.getEmail(), SUBJECT_LINE, MAIL_TEXT_BEGINNING + htmlLink + MAIL_TEXT_ENDING + MAIL_TEXT_GREETING + MAIL_TEXT_SIGNATURE);
        } catch(MessagingException e) {
            return "mailError";
        }

        return SUCCESS;
    }
}
