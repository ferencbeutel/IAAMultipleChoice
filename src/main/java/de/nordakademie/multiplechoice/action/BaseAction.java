package de.nordakademie.multiplechoice.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.User;
import de.nordakademie.multiplechoice.service.UserService;
import lombok.Setter;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by ferencbeutel on 22.10.16.
 * If you need Session Support in your Action, extend this Base Action class
 */
public abstract class BaseAction extends ActionSupport implements SessionAware {
    @Setter
    private Map<String, Object> session;

    @Autowired
    private UserService userService;

    public boolean isUserLoggedIn() {
        if(session.containsKey("userMail")) {
            return true;
        }
        return false;
    }

    /**
     * specific getter for retrieving an updated user object for the user mail
     * @return
     */
    public User getUserFromSession() throws NotLoggedInException {
        Object userMailObject = session.get("userMail");
        if(userMailObject == null) {
            throw new NotLoggedInException();
        }
        String userMail = (String) userMailObject;
        return userService.byMail(userMail);
    }

    public void SetUserInSession(String userMail, String userType) {
        session.put("userMail", userMail);
        session.put("userType", userType);
    }

    public void logOutUser() {
        session.remove("userMail");
    }

    public boolean isUserLecturer(User user) {
        return userService.isUserLecturer(user);
    }
    public boolean isUserStudent(User user) {
        return userService.isUserStudent(user);
    }
}
