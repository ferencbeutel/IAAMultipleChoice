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
 * This class is responsible for submitting a performed test and calculating and persisting the result
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
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

    /**
     * This method performs the submitting of a test which was performed by a student and calculates the result
     * @return a String  which is used to select a result element in struts
     * @throws NotLoggedInException
     * @throws InsufficientPermissionsException
     * @throws GenericErrorException
     */
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
