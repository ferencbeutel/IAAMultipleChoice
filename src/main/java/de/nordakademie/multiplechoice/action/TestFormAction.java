package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.InsufficientPermissionsException;
import de.nordakademie.multiplechoice.exception.NoUserInSessionException;
import de.nordakademie.multiplechoice.model.CreditPointsType;
import de.nordakademie.multiplechoice.model.EvaluationType;
import de.nordakademie.multiplechoice.model.User;
import lombok.Getter;

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


    public String openForm() throws NoUserInSessionException, InsufficientPermissionsException{
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
        return SUCCESS;
        }

    }


