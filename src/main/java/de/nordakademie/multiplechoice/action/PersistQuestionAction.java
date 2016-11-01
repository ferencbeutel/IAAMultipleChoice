package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.exception.InsufficientPermissionsException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.Question;
import de.nordakademie.multiplechoice.model.UserType;
import de.nordakademie.multiplechoice.service.QuestionService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Melanie on 01.11.2016.
 */
public class PersistQuestionAction extends BaseAction {
    @Autowired
    private QuestionService questionService;

    @Getter
    @Setter
    private Question question;

    @Getter
    @Setter
    private long questionId;

    public String persistQuestion() throws NotLoggedInException, InsufficientPermissionsException, GenericErrorException {
        if(getUserType() != UserType.LECTURER) {
            throw new InsufficientPermissionsException();
        }
        question= questionService.byId(questionId);

        questionService.updateQuestion(question);
        return SUCCESS;
    }
}
