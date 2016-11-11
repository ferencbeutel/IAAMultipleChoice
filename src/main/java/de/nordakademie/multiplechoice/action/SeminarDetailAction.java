package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.service.SeminarService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class is responsible for preapring the detail-data of a seminar
 *
 * @author Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
public class SeminarDetailAction extends BaseAction {

  @Autowired
  SeminarService seminarService;
  @Getter
  Seminar seminar;
  @Setter
  @Getter
  private long seminarId;

  /**
   * This method prepares the details of a seminar fpr the detail page
   *
   * @return a String  which is used to select a result element in struts
   *
   * @throws GenericErrorException
   */
  public String displaySeminarDetail() throws GenericErrorException {
    seminar = seminarService.byId(seminarId);

    return SUCCESS;
  }
}
