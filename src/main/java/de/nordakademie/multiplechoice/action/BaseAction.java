package de.nordakademie.multiplechoice.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.Lecturer;
import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.model.User;
import de.nordakademie.multiplechoice.model.UserType;
import de.nordakademie.multiplechoice.service.LecturerService;
import de.nordakademie.multiplechoice.service.StudentService;
import lombok.Setter;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by ferencbeutel on 22.10.16.
 * If you need Session Support in your Action, extend this Base Action class
 */
public abstract class BaseAction extends ActionSupport implements SessionAware {
    @Setter
    private Map<String, Object> session;

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private StudentService studentService;

    private HttpServletRequest request = ServletActionContext.getRequest();
    private Locale userLocale = request.getLocale();
    private ResourceBundle messages = ResourceBundle.getBundle("messages", userLocale);

    public boolean isUserLoggedIn() {
        if(session.containsKey("userMail")) {
            return true;
        }
        return false;
    }

    public UserType getUserType() throws NotLoggedInException {
        Object userType = session.get("userType");
        if(userType == null) {
            throw new NotLoggedInException();
        }
        return (UserType) userType;
    }

    public Student getStudentFromSession() throws NotLoggedInException, GenericErrorException {
        String userMail = getUserMail();

        Student student = studentService.findByMail(userMail);
        if(student == null) {
            throw new GenericErrorException();
        }
        return student;
    }

    public Lecturer getLecturerFromSession() throws NotLoggedInException, GenericErrorException {
        String userMail = getUserMail();

        Lecturer lecturer = lecturerService.findByMail(userMail);
        if(lecturer == null) {
            throw new GenericErrorException();
        }
        return lecturer;
    }

    public void setUserInSession(final String userMail, final UserType userType) {
        session.put("userMail", userMail);
        session.put("userType", userType);
    }

    public void logOutUser() {
        session.remove("userMail");
        session.remove("userType");
    }

    private String getUserMail() throws NotLoggedInException {
        Object userMailObject = session.get("userMail");
        if(userMailObject == null) {
            throw new NotLoggedInException();
        }
        return userMailObject.toString();
    }

    public String getI18NValue(final String key) {
        return StringEscapeUtils.unescapeHtml4(messages.getString(key));
    }
}
