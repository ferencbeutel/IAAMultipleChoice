package de.nordakademie.multiplechoice.action;

import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Created by ferencbeutel on 22.10.16.
 * If you need Session Support in your Action, extend this Base Action class
 */
public abstract class BaseAction extends ActionSupport implements SessionAware {
    @Getter
    @Setter
    Map<String, Object> session;

    /**
     * Check whether a user is logged in or not
     * If not, redirect to Login Page
     */
    public boolean isUserLoggedIn() {
        if(!session.containsKey("user")) {
            return false;
        }

        return true;
    }
}
