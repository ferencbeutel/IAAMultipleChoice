package de.nordakademie.multiplechoice.action;

/**
 * This class is responsible for the log out from the application
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */


public class LogoutAction extends BaseAction {

    /**
     * This method performs the log-out of an user
     * @return a String  which is used to select a result element in struts
     */
    public String logOut() {
        logOutUser();
        return SUCCESS;
    }
}
