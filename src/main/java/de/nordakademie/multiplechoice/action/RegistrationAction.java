package de.nordakademie.multiplechoice.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.model.User;
import de.nordakademie.multiplechoice.service.MailService;
import de.nordakademie.multiplechoice.service.StudentService;
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
public class RegistrationAction extends BaseAction {

    private static final String SUBJECT_LINE = "Your registration at the Nordakademie Seminartool";
    private static final String MAIL_TEXT_BEGINNING = "<p>Thank you for registering at the Nordakademie Seminartool. Please open the following link to complete your registration</p>";
    private static final String MAIL_TEXT_ENDING = "<p>If you cant open the link directly, try to copy and paste it directly into your browser.</p>";
    private static final String MAIL_TEXT_GREETING = "<p>Yours sincerely,</p>";
    private static final String MAIL_TEXT_SIGNATURE = "<p>The Nordakademie Seminartool</p>";

    @Autowired
    UUIDService uuidService;

    @Autowired
    StudentService studentService;

    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;

    @Getter
    @Setter
    private User user;

    public String register() {
        if(isUserLoggedIn()) {
            return "alreadyLoggedInError";
        }

        final String uuid = uuidService.getUUID();
        user.setRegToken(uuid);
        user.setRegComplete(false);
        final Student student = new Student();
        student.setUser(user);
        studentService.saveStudent(student);
        sendConfirmationMail();
        return SUCCESS;
    }


    private String sendConfirmationMail() {
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

    public void validate() {
        if(user.getName() == null || user.getName().length() < 2) {
            addFieldError("name", "Please enter your First Name");
        }
        if(user.getSurName() == null || user.getSurName().length() < 2) {
            addFieldError("surName", "Please enter your Last Name");
        }
        if(user.getEmail() == null || user.getEmail().length() == 0) {
            addFieldError("surName", "Please enter your Nordakademie E-Mail address");
        } else {
            String[] domains = user.getEmail().split("@");
            if (!domains[domains.length - 1].equals("nordakademie.de")) {
                addFieldError("mail", "Please use your Nordakademie E-Mail address");
            } else {
                if(userService.byMail(user.getEmail()) != null) {
                    addFieldError("mail", "This E-Mail is already registered.");
                }
            }
        }
        if(user.getPassword() == null || user.getPassword().length() < 8) {
            addFieldError("password", "Please enter a password with a length of at least 8");
        } else {
            int fulfilledCriteria = 0;
            if (user.getPassword().matches(".*\\d+.*")) {
                fulfilledCriteria ++;
            }
            if(!user.getPassword().equals(user.getPassword().toLowerCase())) {
                fulfilledCriteria ++;
            }
            if(!user.getPassword().equals(user.getPassword().toUpperCase())) {
                fulfilledCriteria ++;
            }
            if(!user.getPassword().matches("[a-zA-Z0-9 ]*")) {
                fulfilledCriteria ++;
            }

            if (fulfilledCriteria < 3) {
                addFieldError("password", "Please fulfill at least 3 of the mentioned criteria for your password");
            }
        }
    }
}
