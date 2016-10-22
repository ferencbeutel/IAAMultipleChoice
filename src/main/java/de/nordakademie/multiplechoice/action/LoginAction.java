package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.model.User;
import de.nordakademie.multiplechoice.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Ferenc on 19.10.2016.
 */
public class LoginAction extends BaseAction {
    @Getter
    @Setter
    private String mail;

    @Getter
    @Setter
    private String password;

    @Autowired
    private UserService userService;

    public String login() {
        if(isUserLoggedIn()) {
            return "alreadyLoggedInError";
        }

        session.put("user", userService.byMail(mail));
        return SUCCESS;
    }

    public void validate() {

        final User user = userService.byMail(mail);
        if (mail == null || mail.length() == 0) {
            addFieldError("mail", "Please enter your E-Mail-Address");
        } else {
            String[] domains = mail.split("@");
            if (!domains[domains.length - 1].equals("nordakademie.de")) {
                addFieldError("mail", "Please only use your Nordakademie E-Mail address");
            } else if (user == null) {
                addFieldError("mail", "Please enter a valid E-Mail");
            } else if (!user.isRegComplete()) {
                addFieldError("mail", "Please validate your account before logging in by clicking on the link we sent to your E-Mail Account.");
            }
        }

        if (password == null || password.length() == 0) {
            addFieldError("password", "Please enter your valid Password");
        } else if (user != null) {
            if (!user.getPassword().equals(password)) {
                addFieldError("password", "Wrong password");
            }
        }
    }
}
