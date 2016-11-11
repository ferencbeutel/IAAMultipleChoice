package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.AlreadyLoggedInException;
import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.model.User;
import de.nordakademie.multiplechoice.model.UserType;
import de.nordakademie.multiplechoice.service.LecturerService;
import de.nordakademie.multiplechoice.service.StudentService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class is responsible for login to the application
 *
 * @author Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
public class LoginAction extends BaseAction {

  @Autowired
  private StudentService studentService;

  @Autowired
  private LecturerService lecturerService;

  @Getter
  @Setter
  private String mail;

  @Getter
  @Setter
  private String password;

  /**
   * This method performs the login of an user
   *
   * @return a String  which is used to select a result element in struts
   *
   * @throws AlreadyLoggedInException
   * @throws GenericErrorException    if no lecturer or student can be found by their mail
   */
  public String login() throws AlreadyLoggedInException, GenericErrorException {
    if (isUserLoggedIn()) {
      throw new AlreadyLoggedInException();
    }
    if (studentService.findByMail(mail) != null) {
      setUserInSession(mail, UserType.STUDENT);
      return SUCCESS;
    }
    if (lecturerService.findByMail(mail) != null) {
      setUserInSession(mail, UserType.LECTURER);
      return SUCCESS;
    }
    // should not happen because the validate method checks whether the user can be logged in or not
    throw new GenericErrorException();
  }

  /**
   * This method validates the inputs of the login fields and adds possibly fieldErrors
   */
  public void validate() {

    User user = lecturerService.findByMail(mail);
    if (user == null) {
      user = studentService.findByMail(mail);
    }

    if (mail == null || mail.length() == 0) {
      addFieldError("mail", getI18NValue("loginFieldError.mail"));
    } else {
      String[] domains = mail.split("@");
      if (!domains[domains.length - 1].equals("nordakademie.de")) {
        addFieldError("mail", getI18NValue("loginFieldError.nak"));
      } else if (user == null) {
        addFieldError("mail", getI18NValue("loginFieldError.mailValid"));
      } else if (!user.isRegComplete()) {
        addFieldError("mail", getI18NValue("loginFieldError.activation"));
      }
    }

    if (password == null || password.length() == 0) {
      addFieldError("password", getI18NValue("loginFieldError.validPassword"));
    } else if (user != null && !user.getPassword().equals(password)) {
      addFieldError("password", getI18NValue("loginFieldError.wrongPassword"));
    }
  }
}
