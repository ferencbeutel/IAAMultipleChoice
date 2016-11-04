package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.exception.InsufficientPermissionsException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.*;
import de.nordakademie.multiplechoice.service.SeminarService;
import de.nordakademie.multiplechoice.service.TestResultService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ferencbeutel on 02.11.16.
 */
public class TestSubmitAction extends BaseAction {

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private TestResultService testResultService;

    @Getter
    @Setter
    private List<Long> singleMultiChoiceInput = new ArrayList<>();

    @Getter
    @Setter
    private List<String> gapInput = new ArrayList<>();

    @Getter
    @Setter
    private long seminarId;

    @Setter
    private long testResultId;

    @Getter
    private TestResult result;

    public String submitTest() throws NotLoggedInException, InsufficientPermissionsException, GenericErrorException {
        if(getUserType() != UserType.STUDENT) {
            throw new InsufficientPermissionsException();
        }
        Test test = seminarService.byId(seminarId).getTest();

        List<Answer> studentAnswers = new ArrayList<>();
        for(Long answerId : singleMultiChoiceInput) {
            Answer answer = new Answer();
            answer.setAnswerId(answerId);
            studentAnswers.add(answer);
        }
        for(String gapAnswer : gapInput) {
            String[] splitted = gapAnswer.split("\\|");
            Answer answer = new Answer();
            answer.setAnswerId(Long.valueOf(splitted[1]));
            answer.setText(splitted[0]);
            studentAnswers.add(answer);
        }
        result = testResultService.byId(testResultId);
        result.setPoints(test.calculateTestResult(studentAnswers));
        testResultService.createOrUpdate(result);

        return SUCCESS;
    }
}
