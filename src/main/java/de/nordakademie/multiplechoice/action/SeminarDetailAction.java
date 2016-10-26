package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.service.SeminarService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ferencbeutel on 25.10.16.
 */
public class SeminarDetailAction extends BaseAction {

    @Autowired
    SeminarService seminarService;

    @Setter
    private long seminarId;

    @Getter
    Seminar seminar;

    public String displaySeminarDetail() throws GenericErrorException {
        seminar = seminarService.byId(seminarId);
        if(seminar == null) {
            throw new GenericErrorException();
        }
        return SUCCESS;
    }
}
