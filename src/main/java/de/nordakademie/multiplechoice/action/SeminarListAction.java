package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.model.Seminar;
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

    public String displaySeminarList() {
        if (!isUserLoggedIn()) {
            return "notLoggedInError";
        }
        Object userObject = session.get("user");
        if(!isStudent(userObject)) {
            return "insufficientPermissionError";
        }
        seminarList = seminarService.listAll();
        return SUCCESS;
    }
}
