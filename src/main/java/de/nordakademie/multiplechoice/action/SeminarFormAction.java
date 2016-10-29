package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.InsufficientPermissionsException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.User;
import de.nordakademie.multiplechoice.service.SeminarService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ferencbeutel on 26.10.16.
 */
public class SeminarFormAction extends BaseAction {

    @Autowired
    private SeminarService seminarService;

    @Getter
    private Seminar seminar;

    @Setter
    private long seminarId;

    public String openAddForm() throws NotLoggedInException, InsufficientPermissionsException {
        User user = getUserFromSession();
        if(!isUserLecturer(user)) {
            throw new InsufficientPermissionsException();
        }
        return SUCCESS;
    }

    public String openEditForm() throws NotLoggedInException, InsufficientPermissionsException {
        User user = getUserFromSession();
        if(!isUserLecturer(user)) {
            throw new InsufficientPermissionsException();
        }
        seminar = seminarService.byId(seminarId);
        return SUCCESS;
    }
}
