package de.nordakademie.multiplechoice.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.multiplechoice.service.MailService;
import de.nordakademie.multiplechoice.service.UUIDService;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Ferenc on 19.10.2016.
 */
public class RegistrationAcceptationAction extends ActionSupport {

    @Getter
    @Setter
    private String regCode;

    //TODO: unlock User by the UUID once we have a User Dao
    public String acceptRegistration() {
        if(regCode == null) {
            return "emptyRegCodeError";
        }

        System.out.println("WOW, look at that UUID: " + regCode);

        return SUCCESS;
    }
}
