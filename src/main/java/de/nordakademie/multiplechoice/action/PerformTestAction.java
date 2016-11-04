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
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by ferencbeutel on 29.10.16.
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

        test = seminar.getTest();
        testResultId = result.getTestResultId();
        result.setStartDateTime(LocalDateTime.now());
        testResultService.createOrUpdate(result);
        return SUCCESS;
    }

    public void validate() {

        if(!savedAccessToken.equals(inputAccessToken)) {
            addFieldError("accessToken", getI18NValue("performTestFieldError.token"));
        }
    }
}
