package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.InsufficientPermissionsException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.Question;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.Test;
import de.nordakademie.multiplechoice.model.UserType;
import de.nordakademie.multiplechoice.service.QuestionService;
import de.nordakademie.multiplechoice.service.SeminarService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Ferenc on 19.10.2016.
 */
public class QuestionDeleteAction extends BaseAction {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private SeminarService seminarService;

    @Getter
    @Setter
    private long questionId;

    @Getter
    @Setter
    private long seminarId;

    @Getter
    @Setter
    private Seminar seminar;

    @Getter
    @Setter
    private Test test;
    @Getter
    @Setter
    private List<Question> questions;

    public String openForm() throws NotLoggedInException, InsufficientPermissionsException {
        if (getUserType() != UserType.LECTURER) {
            throw new InsufficientPermissionsException();
        }

        return SUCCESS;
    }

    public String deleteQuestion() throws NotLoggedInException, InsufficientPermissionsException {
        if (getUserType() != UserType.LECTURER) {
            throw new InsufficientPermissionsException();
        }
        seminar = seminarService.byId(seminarId);
        test = seminar.getTest();
        questions = test.getQuestions();
        for (Question q: questions) {
            if (q.getQuestionId() == questionId){
                int delPos = q.getPosition();
                questions.remove(q);
                for (Question qOld: questions) {
                    if (qOld.getPosition()>delPos){
                        qOld.setPosition(qOld.getPosition()-1);
                    }
                }
                break;
            }
        }
        test.setQuestions(questions);
        seminar.setTest(test);
        seminarService.createOrUpdate(seminar);
        questionService.deleteQuestion(questionId);
        return SUCCESS;
    }
}


