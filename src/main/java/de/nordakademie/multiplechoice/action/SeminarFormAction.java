package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.exception.InsufficientPermissionsException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.UserType;
import de.nordakademie.multiplechoice.service.SeminarService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class is responsible for preapring the form for adding a seminar
 *
 * @author Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
public class SeminarFormAction extends BaseAction {

  @Autowired
  private SeminarService seminarService;

  @Getter
  private Seminar seminar;

  @Setter
  private long seminarId;

  /**
   * This method prepares the input-form of the add-seminar-page
   *
   * @return a String  which is used to select a result element in struts
   *
   * @throws NotLoggedInException
   * @throws InsufficientPermissionsException
   * @throws GenericErrorException
   */
  public String openAddForm() throws NotLoggedInException, InsufficientPermissionsException, GenericErrorException {
    if (getUserType() != UserType.LECTURER) {
      throw new InsufficientPermissionsException();
    }
    return SUCCESS;
  }

  /**
   * This method prepares the edit-input-form of the edit-seminar-page
   *
   * @return a String  which is used to select a result element in struts
   *
   * @throws NotLoggedInException
   * @throws InsufficientPermissionsException
   */
  public String openEditForm() throws NotLoggedInException, InsufficientPermissionsException {
    if (getUserType() != UserType.LECTURER) {
      throw new InsufficientPermissionsException();
    }
    seminar = seminarService.byId(seminarId);
    return SUCCESS;
  }
}
