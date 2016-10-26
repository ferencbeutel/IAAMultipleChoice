package de.nordakademie.multiplechoice.action;

/**
 * Created by ferencbeutel on 24.10.16.
 */
public class LogoutAction extends BaseAction {
    public String logOut() {
        logOutUser();
        return SUCCESS;
    }
}
