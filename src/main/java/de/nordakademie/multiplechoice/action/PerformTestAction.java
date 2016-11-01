package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.exception.InsufficientPermissionsException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.model.User;
import de.nordakademie.multiplechoice.model.UserType;
import de.nordakademie.multiplechoice.service.SeminarService;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by ferencbeutel on 29.10.16.
 */
public class PerformTestAction extends BaseAction {

    @Autowired
    private SeminarService seminarService;

    @Getter
    @Setter
    private long seminarId;

    @Getter
    @Setter
    private String savedAccessToken;

    @Getter
    @Setter
    private String inputAccessToken;

    @Getter
    private Seminar seminar;

    public String performTest() throws NotLoggedInException, InsufficientPermissionsException, GenericErrorException {
        if(getUserType() != UserType.STUDENT) {
            throw new InsufficientPermissionsException();
        }
        seminar = seminarService.byId(seminarId);
        return SUCCESS;
    }

    public void validate() {



        if(!savedAccessToken.equals(inputAccessToken)) {
            addFieldError("accessToken", getI18NValue("performTestFieldError.token"));
        }
    }
}
