package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.InsufficientPermissionsException;
import de.nordakademie.multiplechoice.exception.NoUserInSessionException;
import de.nordakademie.multiplechoice.model.User;

/**
 * Created by ferencbeutel on 26.10.16.
 */
public class SeminarFormAction extends BaseAction {
    public String openForm() throws NoUserInSessionException, InsufficientPermissionsException {
        User user = getUserFromSession();
        if(!isUserLecturer(user)) {
            throw new InsufficientPermissionsException();
        }
        return SUCCESS;
    }
}
