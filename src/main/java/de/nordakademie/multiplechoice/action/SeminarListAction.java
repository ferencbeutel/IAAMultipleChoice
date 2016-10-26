package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.InsufficientPermissionsException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.User;
import de.nordakademie.multiplechoice.service.SeminarService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ferencbeutel on 25.10.16.
 */
public class SeminarListAction extends BaseAction {
    @Autowired
    SeminarService seminarService;

    @Getter
    List<Seminar> seminarList = new ArrayList<>();

    public String displaySeminarList() throws NotLoggedInException, InsufficientPermissionsException {
        User user = getUserFromSession();

        if(!isUserStudent(user)) {
            throw new InsufficientPermissionsException();
        }
        seminarList = seminarService.listAll();
        return SUCCESS;
    }
}
