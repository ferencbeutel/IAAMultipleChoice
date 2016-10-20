package de.nordakademie.multiplechoice.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.multiplechoice.model.User;
import de.nordakademie.multiplechoice.service.UUIDService;
import de.nordakademie.multiplechoice.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Ferenc on 19.10.2016.
 */
public class RegistrationAction extends ActionSupport {

    @Autowired
    UUIDService uuidService;

    @Autowired
    UserService userService;

    @Getter
    @Setter
    private User user;

    //TODO: actually persist user
    public String register() {
        final String uuid = uuidService.getUUID();
        user.setRegToken(uuid);
        user.setRegComplete(false);
        userService.saveUser(user);
        return SUCCESS;
    }

    public void validate() {
        if(user.getName() == null || user.getName().length() < 2) {
            addFieldError("name", "Please enter your First Name");
        }
        if(user.getSurName() == null || user.getSurName().length() < 2) {
            addFieldError("surName", "Please enter your Last Name");
        }
        if(user.getEmail() == null || user.getEmail().length() == 0) {
            addFieldError("surName", "Please enter your Nordakademie E-Mail address");
        } else {
            String[] domains = user.getEmail().split("@");
            if (!domains[domains.length - 1].equals("nordakademie.de")) {
                addFieldError("mail", "Please use your Nordakademie E-Mail address");
            } else {
                if(userService.byNatID(user.getEmail()) != null) {
                    addFieldError("mail", "This E-Mail is already registered.");
                }
            }
        }
        if(user.getPassword() == null || user.getPassword().length() < 8) {
            addFieldError("password", "Please enter a password with a length of at least 8");
        } else {
            int fulfilledCriteria = 0;
            if (user.getPassword().matches(".*\\d+.*")) {
                fulfilledCriteria ++;
            }
            if(!user.getPassword().equals(user.getPassword().toLowerCase())) {
                fulfilledCriteria ++;
            }
            if(!user.getPassword().equals(user.getPassword().toUpperCase())) {
                fulfilledCriteria ++;
            }
            if(!user.getPassword().matches("[a-zA-Z0-9 ]*")) {
                fulfilledCriteria ++;
            }

            if (fulfilledCriteria < 3) {
                addFieldError("password", "Please fulfill at least 3 of the mentioned criteria for your password");
            }
        }
    }
}
