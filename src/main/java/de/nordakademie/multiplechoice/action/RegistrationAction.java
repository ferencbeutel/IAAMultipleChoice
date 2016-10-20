package de.nordakademie.multiplechoice.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.multiplechoice.service.MailService;
import de.nordakademie.multiplechoice.service.UUIDService;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Ferenc on 19.10.2016.
 */
public class RegistrationAction extends ActionSupport {

    private static final String SUBJECT_LINE = "Your registration at the Nordakademie Seminartool";
    private static final String MAIL_TEXT_BEGINNING = "<p>Thank you for registring at the Nordakademie Seminartool. Please open the following link to complete your registration</p>";
    private static final String MAIL_TEXT_ENDING = "<p>If you cant open the link directly, try to copy and paste it directly into your browser.</p>";
    private static final String MAIL_TEXT_GREETING = "<p>Yours sincerely,</p>";
    private static final String MAIL_TEXT_SIGNATURE = "<p>The Nordakademie Seminartool</p>";

    @Autowired
    UUIDService uuidService;

    @Autowired
    MailService mailService;

    @Getter
    @Setter
    private String regCode;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String mail;

    @Getter
    @Setter
    private String password;

    //TODO: actually persist user
    public String register() {
        return SUCCESS;
    }

    //TODO: Persist User once we have a User Dao
    //TODO: Send Mail to User and not to ferenc..
    public String sendConfirmationMail() {
        final String uuid = uuidService.getUUID();
        HttpServletRequest request = ServletActionContext.getRequest();
        final String protocol = request.getScheme();
        final String host = request.getLocalAddr() + ":" + request.getLocalPort() + "/";
        final String action = "acceptRegistration";
        final String uuidParameterName = "regCode";
        final String link = protocol + "://" + host + action + "?" + uuidParameterName + "=" + uuid;
        final String htmlLink = "<a href=" + link + ">" + link + "</a>";

        try {
            mailService.sendMail("ferenc.beutel@nordakademie.de", SUBJECT_LINE, MAIL_TEXT_BEGINNING + htmlLink + MAIL_TEXT_ENDING + MAIL_TEXT_GREETING + MAIL_TEXT_SIGNATURE);
        } catch(MessagingException e) {
            return "mailError";
        }

        return SUCCESS;
    }

    //TODO: unlock User by the UUID once we have a User Dao
    public String acceptRegistration() {
        if(regCode == null) {
            return "emptyRegCodeError";
        }

        System.out.println("WOW, look at that UUID: " + regCode);

        return SUCCESS;
    }

    public void validate() {
        if(firstName == null || firstName.length() < 2) {
            addFieldError("firstName", "Please enter your First Name");
        }
        if(lastName == null || lastName.length() < 2) {
            addFieldError("lastName", "Please enter your Last Name");
        }
        if(mail == null || mail.length() == 0) {
            addFieldError("lastName", "Please enter your Nordakademie E-Mail address");
        } else {
            String[] domains = mail.split("@");
            if (!domains[domains.length - 1].equals("nordakademie.de")) {
                addFieldError("mail", "Please use your Nordakademie E-Mail address");
            }
        }
        if(password == null || password.length() < 8) {
            addFieldError("password", "Please enter a password with a length of at least 8");
        } else {
            int fulfilledCriteria = 0;
            if (password.matches(".*\\d+.*")) {
                fulfilledCriteria ++;
            }
            if(!password.equals(password.toLowerCase())) {
                fulfilledCriteria ++;
            }
            if(!password.equals(password.toUpperCase())) {
                fulfilledCriteria ++;
            }
            if(!password.matches("[a-zA-Z0-9 ]*")) {
                fulfilledCriteria ++;
            }

            if (fulfilledCriteria < 3) {
                addFieldError("password", "Please fulfill at least 3 of the mentioned criteria for your password");
            }
        }
    }
}
