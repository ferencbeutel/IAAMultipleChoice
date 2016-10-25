package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.model.Lecturer;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.service.SeminarService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Ferenc on 19.10.2016.
 */
public class OpenSeminarAction extends BaseAction {
    public String openForm() {
        if (!isUserLoggedIn()) {
            return "notLoggedInError";
        } else {
            Object userObject = session.get("user");
            try {
                Lecturer lecturer = (Lecturer) userObject;
            } catch (ClassCastException e) {
                return "insufficientPermissionError";
            }
        }
        return SUCCESS;
    }


}
