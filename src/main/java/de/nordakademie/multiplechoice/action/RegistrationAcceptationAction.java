package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.AlreadyLoggedInException;
import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.service.StudentService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class is responsible for the functionality of acceptation and activation of new user accounts
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
public class RegistrationAcceptationAction extends BaseAction {

    @Getter
    @Setter
    private String regCode;

    @Autowired
    private StudentService studentService;

    /**
     * This method performs the acceptation of the registration of a new created user
     * @return a String  which is used to select a result element in struts
     * @throws AlreadyLoggedInException
     * @throws GenericErrorException
     */
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
