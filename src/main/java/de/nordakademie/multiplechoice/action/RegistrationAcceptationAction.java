package de.nordakademie.multiplechoice.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.multiplechoice.model.User;
import de.nordakademie.multiplechoice.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Ferenc on 19.10.2016.
 */
public class RegistrationAcceptationAction extends BaseAction {

    @Getter
    @Setter
    private String regCode;

    @Autowired
    private UserService userService;

    public String acceptRegistration() {
        if(isUserLoggedIn()) {
            return "alreadyLoggedInError";
        }

        if(regCode == null) {
            return "emptyRegCodeError";
        }

        final User userToUnlock = userService.byRegToken(regCode);
        if (userToUnlock == null) {
            return "NoUserFoundForRegCodeError";
        }

        userToUnlock.setRegComplete(true);
        userService.updateUser(userToUnlock);

        System.out.println(userService.byMail(userToUnlock.getEmail()));

        return SUCCESS;
    }
}
