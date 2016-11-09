package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.exception.InsufficientPermissionsException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.*;
import de.nordakademie.multiplechoice.service.SeminarService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 *  This class is responsible for preparing the add and edit form for Test-Creation/Update
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
public class TestFormAction extends BaseAction {

    @Autowired
    private SeminarService seminarService;

    @Getter
    private Seminar seminar;

    @Setter
    private long seminarId;

    /**
     * This method prepares the form of the add Test Page
     * @return a String  which is used to select a result element in struts
     * @throws NotLoggedInException
     * @throws InsufficientPermissionsException
     */
    public String openAddForm() throws NotLoggedInException, InsufficientPermissionsException {
        checkAuth();
        seminar = seminarService.byId(seminarId);
        return SUCCESS;
    }

    /**
     * This method prepares the form of th edit Test Page
     * @return a String  which is used to select a result element in struts
     * @throws NotLoggedInException
     * @throws InsufficientPermissionsException
     */
    public String openEditForm() throws NotLoggedInException, InsufficientPermissionsException {
        checkAuth();
        seminar = seminarService.byId(seminarId);
        return SUCCESS;
    }


    /**
     * This method checks if the user is a lecturer
     * @throws NotLoggedInException
     * @throws InsufficientPermissionsException
     */
    private void checkAuth() throws NotLoggedInException, InsufficientPermissionsException {
        if(getUserType() != UserType.LECTURER) {
            throw new InsufficientPermissionsException();
        }
    }
}


