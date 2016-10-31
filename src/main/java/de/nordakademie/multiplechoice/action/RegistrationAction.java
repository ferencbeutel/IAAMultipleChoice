package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.AlreadyLoggedInException;
import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.model.User;
import de.nordakademie.multiplechoice.service.MailService;
import de.nordakademie.multiplechoice.service.StudentService;
import de.nordakademie.multiplechoice.service.UUIDService;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Ferenc on 19.10.2016.
 */
public class RegistrationAction extends BaseAction {
    static HttpServletRequest request = ServletActionContext.getRequest();
    static Locale userLocale = request.getLocale();
    static ResourceBundle messages = ResourceBundle.getBundle("messages", userLocale);
    private static final String SUBJECT_LINE = messages.getString("registrationMail.subjectLine");
    private static final String MAIL_TEXT_BEGINNING = messages.getString("registrationMail.textBeginning");
    private static final String MAIL_TEXT_ENDING = messages.getString("registrationMail.textEnding");
    private static final String MAIL_TEXT_GREETING = messages.getString("registrationMail.textGreeting");
    private static final String MAIL_TEXT_SIGNATURE = messages.getString("registrationMail.textSignature");

    @Autowired
    UUIDService uuidService;

    @Autowired
    StudentService studentService;

    @Autowired
    MailService mailService;

    @Getter
    @Setter
    private Student student;

    public String register() throws AlreadyLoggedInException {
        if(isUserLoggedIn()) {
            throw new AlreadyLoggedInException();
        }
        final String uuid = uuidService.getUUID();
        student.setRegToken(uuid);
        student.setRegComplete(false);
        studentService.save(student);
        sendConfirmationMail();

        return SUCCESS;
    }


    private String sendConfirmationMail() {
        HttpServletRequest request = ServletActionContext.getRequest();
        final String protocol = request.getScheme();
        final String host = request.getLocalAddr() + ":" + request.getLocalPort() + "/";
        final String action = "acceptRegistration";
        final String uuidParameterName = "regCode";
        final String link = protocol + "://" + host + action + "?" + uuidParameterName + "=" + student.getRegToken();
        final String htmlLink = "<a href=" + link + ">" + link + "</a>";

        try {
            mailService.sendMail(student.getEmail(), SUBJECT_LINE, MAIL_TEXT_BEGINNING + htmlLink + MAIL_TEXT_ENDING + MAIL_TEXT_GREETING + MAIL_TEXT_SIGNATURE);
        } catch(MessagingException e) {
            return "mailError";
        }

        return SUCCESS;
    }

    public void validate() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Locale userLocale = request.getLocale();
        ResourceBundle messages = ResourceBundle.getBundle("messages", userLocale);

        if(student.getName() == null || student.getName().length() < 2) {
            addFieldError("name", messages.getString("registrationFieldError.firstName"));
        }
        if(student.getSurName() == null || student.getSurName().length() < 2) {
            addFieldError("surName", messages.getString("registrationFieldError.lastName"));
        }
        if(student.getEmail() == null || student.getEmail().length() == 0) {
            addFieldError("mail", messages.getString("registrationFieldError.mail"));
        } else {
            String[] domains = student.getEmail().split("@");
            if (!domains[domains.length - 1].equals("nordakademie.de")) {
                addFieldError("mail", messages.getString("registrationFieldError.nakMail"));
            } else {
                if(studentService.findByMail(student.getEmail()) != null) {
                    addFieldError("mail", messages.getString("registrationFieldError.mailRegistrated"));
                }
            }
        }
        if(student.getPassword() == null || student.getPassword().length() < 8) {
            addFieldError("password", messages.getString("registrationFieldError.passwordLen"));
        } else {
            int fulfilledCriteria = 0;
            if (student.getPassword().matches(".*\\d+.*")) {
                fulfilledCriteria ++;
            }
            if(!student.getPassword().equals(student.getPassword().toLowerCase())) {
                fulfilledCriteria ++;
            }
            if(!student.getPassword().equals(student.getPassword().toUpperCase())) {
                fulfilledCriteria ++;
            }
            if(!student.getPassword().matches("[a-zA-Z0-9 ]*")) {
                fulfilledCriteria ++;
            }

            if (fulfilledCriteria < 3) {
                addFieldError("password", messages.getString("registrationFieldError.passwordCriteria"));
            }
        }
    }
}
