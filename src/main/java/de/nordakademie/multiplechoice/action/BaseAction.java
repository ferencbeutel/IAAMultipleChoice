package de.nordakademie.multiplechoice.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.Lecturer;
import de.nordakademie.multiplechoice.model.Student;
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
 * If Session Support is needed in an Action, this Base Action class can be use to extend
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 * If Session Support is needed in an Action, this Base Action class can be use to extend
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

    /**
     * This method checks whether a user is logged in or not
     * @return a boolean (true = logged in // false = not logged in)
     */
    public boolean isUserLoggedIn() {
        if(session.containsKey("userMail")) {
            return true;
        }
        return false;
    }

    /**
     * This method returns the UserType of an User
     * @return a UserType (Student or Lecturer)
     * @throws NotLoggedInException
     */
    public UserType getUserType() throws NotLoggedInException {
        Object userType = session.get("userType");
        if(userType == null) {
            throw new NotLoggedInException();
        }
        return (UserType) userType;
    }

    /**
     * This method returns the student of the current session
     * @return a Student
     * @throws NotLoggedInException
     * @throws GenericErrorException if there wasn't found a student for this session
     */
    public Student getStudentFromSession() throws NotLoggedInException, GenericErrorException {
        String userMail = getUserMail();

        Student student = studentService.findByMail(userMail);
        if(student == null) {
            throw new GenericErrorException();
        }
        return student;
    }

    /**
     * This method returns the lecturer of the current session
     * @return a Lecturer
     * @throws NotLoggedInException
     * @throws GenericErrorException if there wasn't found a lecturer for this session
     */
    public Lecturer getLecturerFromSession() throws NotLoggedInException, GenericErrorException {
        String userMail = getUserMail();

        Lecturer lecturer = lecturerService.findByMail(userMail);
        if(lecturer == null) {
            throw new GenericErrorException();
        }
        return lecturer;
    }

    /**
     * This method sets the user for the current session
     * @param userMail Mail adress of the user
     * @param userType UserType of the user (Lecturer or Student)
     */
    public void setUserInSession(final String userMail, final UserType userType) {
        session.put("userMail", userMail);
        session.put("userType", userType);
    }

    /**
     * This method performs the log out of user (remove the user from the session)
     */
    public void logOutUser() {
        session.remove("userMail");
        session.remove("userType");
    }

    /**
     * This method returns the mail-adress of the current user
     * @return a String with an mail-adress
     * @throws NotLoggedInException
     */
    private String getUserMail() throws NotLoggedInException {
        Object userMailObject = session.get("userMail");
        if(userMailObject == null) {
            throw new NotLoggedInException();
        }
        return userMailObject.toString();
    }

    /**
     * This method gets the internationalized text of a key
     * @param key key for the text
     * @return a String with the appropriate text
     */
    public String getI18NValue(final String key) {
        return StringEscapeUtils.unescapeHtml4(messages.getString(key));
    }
}
