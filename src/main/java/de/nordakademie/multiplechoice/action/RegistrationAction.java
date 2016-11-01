package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.AlreadyLoggedInException;
import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.service.MailService;
import de.nordakademie.multiplechoice.service.StudentService;
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
public class RegistrationAction extends BaseAction {
    private final String subjectLine = getI18NValue("registrationMail.subjectLine");
    private final String mailTextBeginning = getI18NValue("registrationMail.textBeginning");
    private final String mailTextEnding = getI18NValue("registrationMail.textEnding");
    private final String mailTextGreeting = getI18NValue("registrationMail.textGreeting");
    private final String mailTextSignature = getI18NValue("registrationMail.textSignature");

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
            mailService.sendMail(student.getEmail(), subjectLine, mailTextBeginning + htmlLink + mailTextEnding + mailTextGreeting + mailTextSignature);
        } catch(MessagingException e) {
            return "mailError";
        }

        return SUCCESS;
    }

    public void validate() {




        if(student.getName() == null || student.getName().length() < 2) {
            addFieldError("name", getI18NValue("registrationFieldError.firstName"));
        }
        if(student.getSurName() == null || student.getSurName().length() < 2) {
            addFieldError("surName", getI18NValue("registrationFieldError.lastName"));
        }
        if(student.getEmail() == null || student.getEmail().length() == 0) {
            addFieldError("mail", getI18NValue("registrationFieldError.mail"));
        } else {
            String[] domains = student.getEmail().split("@");
            if (!domains[domains.length - 1].equals("nordakademie.de")) {
                addFieldError("mail", getI18NValue("registrationFieldError.nakMail"));
            } else {
                if(studentService.findByMail(student.getEmail()) != null) {
                    addFieldError("mail", getI18NValue("registrationFieldError.mailRegistrated"));
                }
            }
        }
        if(student.getPassword() == null || student.getPassword().length() < 8) {
            addFieldError("password", getI18NValue("registrationFieldError.passwordLen"));
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
                addFieldError("password", getI18NValue("registrationFieldError.passwordCriteria"));
            }
        }
    }
}
