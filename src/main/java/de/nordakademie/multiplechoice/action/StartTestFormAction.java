package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.exception.InsufficientPermissionsException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.*;
import de.nordakademie.multiplechoice.service.MailScheduler;
import de.nordakademie.multiplechoice.service.SeminarService;
import de.nordakademie.multiplechoice.service.StudentService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ferencbeutel on 29.10.16.
 */
public class StartTestFormAction extends BaseAction {

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private StudentService studentService;

    //TODO: remove, only here for testing purposes
    @Autowired
    private MailScheduler mailScheduler;

    @Getter
    @Setter
    private long seminarId;

    @Getter
    private String savedAccessToken;

    public String openStartForm() throws NotLoggedInException, InsufficientPermissionsException, GenericErrorException {
        User user = getUserFromSession();
        if(!isUserStudent(user)) {
            throw new InsufficientPermissionsException();
        }
        mailScheduler.sendTestToken();
        Student student = studentService.byUserId(user.getId());
        Test test = seminarService.byId(seminarId).getTest();

        List<TestResult> testResultIntersection = new ArrayList<>(CollectionUtils.intersection(test.getResults(), student.getResults()));
        // There should be only one test result which the student AND the test contain in their results
        if(testResultIntersection.size() != 1) {
            throw new GenericErrorException();
        }

        savedAccessToken = testResultIntersection.get(0).getAccessToken();
        return SUCCESS;
    }
}
