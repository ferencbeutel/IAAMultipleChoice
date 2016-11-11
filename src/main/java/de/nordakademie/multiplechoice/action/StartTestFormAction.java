package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.exception.InsufficientPermissionsException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.model.Test;
import de.nordakademie.multiplechoice.model.TestResult;
import de.nordakademie.multiplechoice.model.UserType;
import de.nordakademie.multiplechoice.service.MailScheduler;
import de.nordakademie.multiplechoice.service.SeminarService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for preparing the form for starting a test
 *
 * @author Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
public class StartTestFormAction extends BaseAction {

  @Autowired
  private SeminarService seminarService;

  //TODO: remove, only here for testing purposes
  @Autowired
  private MailScheduler mailScheduler;

  @Getter
  @Setter
  private long seminarId;

  @Getter
  private String savedAccessToken;

  /**
   * This method prepares the form of the test-perform page
   *
   * @return a String  which is used to select a result element in struts
   *
   * @throws NotLoggedInException
   * @throws InsufficientPermissionsException
   * @throws GenericErrorException            if there is not only one test result
   */
  public String openStartForm() throws NotLoggedInException, InsufficientPermissionsException, GenericErrorException {
    if (getUserType() != UserType.STUDENT) {
      throw new InsufficientPermissionsException();
    }
    Student student = getStudentFromSession();
    Test test = seminarService.byId(seminarId).getTest();

    List<TestResult> testResultIntersection =
        new ArrayList<>(CollectionUtils.intersection(test.getResults(), student.getResults()));
    // There should be only one test result which the student AND the test contain in their results
    if (testResultIntersection.size() != 1) {
      throw new GenericErrorException();
    }

    savedAccessToken = testResultIntersection.get(0).getAccessToken();
    return SUCCESS;
  }
}
