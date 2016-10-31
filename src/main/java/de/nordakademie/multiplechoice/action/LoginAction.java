package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.AlreadyLoggedInException;
import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.model.Lecturer;
import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.model.User;
import de.nordakademie.multiplechoice.model.UserType;
import de.nordakademie.multiplechoice.service.LecturerService;
import de.nordakademie.multiplechoice.service.StudentService;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Ferenc on 19.10.2016.
 */
public class LoginAction extends BaseAction {

    @Autowired
    private StudentService studentService;

    @Autowired
    private LecturerService lecturerService;

    @Getter
    @Setter
    private String mail;

    @Getter
    @Setter
    private String password;

    public String login() throws AlreadyLoggedInException, GenericErrorException {
        if (isUserLoggedIn()) {
            throw new AlreadyLoggedInException();
        }
        if (studentService.findByMail(mail) != null) {
            setUserInSession(mail, UserType.STUDENT);
            return SUCCESS;
        }
        if (lecturerService.findByMail(mail) != null) {
            setUserInSession(mail, UserType.LECTURER);
            return SUCCESS;
        }
        // should not happen because the validate method checks whether the user can be logged in or not
        throw new GenericErrorException();
    }

    public void validate() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Locale userLocale = request.getLocale();
        ResourceBundle messages = ResourceBundle.getBundle("messages", userLocale);
        User user = lecturerService.findByMail(mail);
        if (user == null) {
            user = studentService.findByMail(mail);
        }

        if (mail == null || mail.length() == 0) {
            addFieldError("mail", messages.getString("loginFieldError.mail"));
        } else {
            String[] domains = mail.split("@");
            if (!domains[domains.length - 1].equals("nordakademie.de")) {
                addFieldError("mail", messages.getString("loginFieldError.nak"));
            } else if (user == null) {
                addFieldError("mail", messages.getString("loginFieldError.mailValid"));
            } else if (!user.isRegComplete()) {
                addFieldError("mail", messages.getString("loginFieldError.activation"));
            }
        }

        if (password == null || password.length() == 0) {
            addFieldError("password", messages.getString("loginFieldError.validPassword"));
        } else if (user != null && !user.getPassword().equals(password)) {
            addFieldError("password", messages.getString("loginFieldError.wrongPassword"));
        }
    }
}
