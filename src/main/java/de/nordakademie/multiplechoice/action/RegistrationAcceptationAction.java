package de.nordakademie.multiplechoice.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.multiplechoice.exception.AlreadyLoggedInException;
import de.nordakademie.multiplechoice.exception.GenericErrorException;
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

    public String acceptRegistration() throws AlreadyLoggedInException, GenericErrorException {
        if(isUserLoggedIn()) {
            throw new AlreadyLoggedInException();
        }
        if(regCode == null) {
            throw new GenericErrorException();
        }
        final User userToUnlock = userService.byRegToken(regCode);
        if (userToUnlock == null) {
            throw new GenericErrorException();
        }

        userToUnlock.setRegComplete(true);
        userService.updateUser(userToUnlock);

        return SUCCESS;
    }
}
