package de.nordakademie.multiplechoice.action;

/**
 * Created by ferencbeutel on 24.10.16.
 */
public class LogoutAction extends BaseAction {
    public String logOut() {
        if(session.containsKey("user")) {
            session.remove("user");
            return SUCCESS;
        } else {
            return "notLoggedInError";
        }
    }
}
