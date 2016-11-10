package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.exception.InsufficientPermissionsException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.exception.TestAlreadyStartedException;
import de.nordakademie.multiplechoice.model.*;
import de.nordakademie.multiplechoice.service.SeminarService;
import de.nordakademie.multiplechoice.service.TestResultService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 * This class is responsible for the preparing the test which the student wants to perform
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
public class PerformTestAction extends BaseAction {

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private TestResultService testResultService;

    @Getter
    @Setter
    private long seminarId;

    @Getter
    @Setter
    private String savedAccessToken;

    @Getter
    @Setter
    private String inputAccessToken;

    @Getter
    private Seminar seminar;

    @Getter
    private Test test;

    @Getter
    private long testResultId;

    /**
     * This method prepares the execution of an test
     * @return  a String  which is used to select a result element in struts
     * @throws NotLoggedInException
     * @throws InsufficientPermissionsException
     * @throws GenericErrorException
     * @throws TestAlreadyStartedException
     */
    public String performTest() throws NotLoggedInException, InsufficientPermissionsException, GenericErrorException, TestAlreadyStartedException {
        if(getUserType() != UserType.STUDENT) {
            throw new InsufficientPermissionsException();
        }
        Student student = getStudentFromSession();
        seminar = seminarService.byId(seminarId);
        TestResult result = new ArrayList<>(CollectionUtils.intersection(student.getResults(), seminar.getTest().getResults())).get(0);
        if(result.getStartDateTime() != null) {
            throw new TestAlreadyStartedException();
        }
        result.setPoints(0);

        test = seminar.getTest();
        testResultId = result.getTestResultId();
        result.setStartDateTime(LocalDateTime.now());
        testResultService.createOrUpdate(result);
        return SUCCESS;
    }

    /**
     * This method checks, if the accessToken is valid
     */
    public void validate() {
        if(!savedAccessToken.equals(inputAccessToken)) {
            addFieldError("accessToken", getI18NValue("performTestFieldError.token"));
        }
    }
}
