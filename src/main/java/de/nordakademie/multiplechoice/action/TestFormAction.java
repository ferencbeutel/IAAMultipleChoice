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
 * Created by Ferenc on 19.10.2016.
 */
public class TestFormAction extends BaseAction {

    @Autowired
    private SeminarService seminarService;

    @Getter
    private Seminar seminar;

    @Setter
    private long seminarId;

    public String openAddForm() throws NotLoggedInException, InsufficientPermissionsException {
        checkAuth();
        seminar = seminarService.byId(seminarId);
        return SUCCESS;
    }

    public String openEditForm() throws NotLoggedInException, InsufficientPermissionsException {
        checkAuth();
        seminar = seminarService.byId(seminarId);
        return SUCCESS;
    }

    private void checkAuth() throws NotLoggedInException, InsufficientPermissionsException {
        User user = getUserFromSession();
        if (!isUserLecturer(user)) {
            throw new InsufficientPermissionsException();
        }
    }
}


