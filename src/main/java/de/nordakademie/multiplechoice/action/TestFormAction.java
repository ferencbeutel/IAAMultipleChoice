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
    private List<String> evaluationTypes;
    @Getter
    private List<String> creditPointsTypes;

    @Autowired
    SeminarService seminarService;

    @Setter
    private long seminarId;

    @Getter
    Seminar seminar;


    public String openForm() throws NotLoggedInException, InsufficientPermissionsException{
        User user = getUserFromSession();
        if(!isUserLecturer(user)) {
            throw new InsufficientPermissionsException();
        }
        evaluationTypes = new ArrayList<>();
        for (EvaluationType evaluationType: EvaluationType.values()) {
             evaluationTypes.add(evaluationType.toString());
        }
        creditPointsTypes = new ArrayList<String>();
        for (CreditPointsType creditPointsType: CreditPointsType.values()) {
            creditPointsTypes.add(creditPointsType.toString());
        }
        seminar = seminarService.byId(seminarId);
        if(seminar == null) {
            return "seminarNotFoundError";
        }
        return SUCCESS;

        }


    }


