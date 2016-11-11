package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.InsufficientPermissionsException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.UserType;
import de.nordakademie.multiplechoice.service.SeminarService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is responsible for preparing the data for the seminar list
 *
 * @author Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
public class SeminarListAction extends BaseAction {
  @Autowired
  SeminarService seminarService;

  @Getter
  List<Seminar> seminarList = new ArrayList<>();

  /**
   * This method prepares the data for the seminar list
   *
   * @return a String  which is used to select a result element in struts
   *
   * @throws NotLoggedInException
   * @throws InsufficientPermissionsException
   */
  public String displaySeminarList() throws NotLoggedInException, InsufficientPermissionsException {
    if (getUserType() != UserType.STUDENT) {
      throw new InsufficientPermissionsException();
    }

    seminarList = seminarService.listAll();
    //Sort seminarList by begindate (desc)
    seminarList =
        seminarList.stream().sorted((seminar1, seminar2) -> seminar1.getBeginDate().compareTo(seminar2.getBeginDate()))
                   .collect(Collectors.toList());
    Collections.reverse(seminarList);
    return SUCCESS;
  }
}
