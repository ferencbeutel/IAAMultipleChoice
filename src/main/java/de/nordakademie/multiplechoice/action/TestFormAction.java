package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.InsufficientPermissionsException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.CreditPointsType;
import de.nordakademie.multiplechoice.model.EvaluationType;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.User;
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

    @Getter
    @Setter
    String seminarName;


    public String openForm() throws NotLoggedInException, InsufficientPermissionsException{
        User user = getUserFromSession();
        if(!isUserLecturer(user)) {
            throw new InsufficientPermissionsException();
        }
        return SUCCESS;

        }


    }


