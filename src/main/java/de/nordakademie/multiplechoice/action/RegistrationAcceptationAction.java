package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.AlreadyLoggedInException;
import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.service.StudentService;
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
    private StudentService studentService;

    public String acceptRegistration() throws AlreadyLoggedInException, GenericErrorException {
        if(isUserLoggedIn()) {
            throw new AlreadyLoggedInException();
        }
        final Student studentToUnlock = studentService.findByRegToken(regCode);

        studentToUnlock.setRegComplete(true);
        studentService.createOrUpdate(studentToUnlock);

        return SUCCESS;
    }
}
