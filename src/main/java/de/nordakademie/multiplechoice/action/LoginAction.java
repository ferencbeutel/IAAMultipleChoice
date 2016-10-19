package de.nordakademie.multiplechoice.action;

import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Ferenc on 19.10.2016.
 */
public class LoginAction extends ActionSupport {
    @Getter
    @Setter
    private String mail;

    @Getter
    @Setter
    private String password;

    //TODO: Actually log the user in
    public String login() {
        return SUCCESS;
    }

    //TODO: Valide User existance and correct password
    public void validate() {

        if (mail == null || mail.length() == 0) {
            addFieldError("mail", "Please enter your E-Mail-Address");
        } else {
            String[] domains = mail.split("@");
            if (!domains[domains.length - 1].equals("nordakademie.de")) {
                addFieldError("mail", "Please only use your Nordakademie E-Mail adress");
            }
        }

        if (password  == null || password.length() == 0) {
            addFieldError("password", "Please enter your valid Password");
        }
    }
}
