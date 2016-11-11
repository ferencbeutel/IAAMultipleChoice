package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.Lecturer;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.model.UserType;
import de.nordakademie.multiplechoice.service.LecturerService;
import de.nordakademie.multiplechoice.service.StudentService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is responsible for the contents on the home page
 *
 * @author Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
@Getter
@Setter
public class HomeAction extends BaseAction {

  @Autowired
  private StudentService studentService;

  @Autowired
  private LecturerService lecturerService;

  @Getter
  private Set<Seminar> seminarList = new HashSet<>();

  @Getter
  private LocalDate now = LocalDate.now();

  @Getter
  private Student student;

  @Getter
  private Lecturer lecturer;

  /**
   * This method prepares the contents for the home-page
   *
   * @return a String which is used to select a result element in struts
   *
   * @throws GenericErrorException if a user can't be fetched from a session
   */
  public String execute() throws GenericErrorException {
    if (!isUserLoggedIn()) {
      return SUCCESS;
    }
    try {
      if (getUserType() == UserType.STUDENT) {
        student = getStudentFromSession();
        seminarList = student.getSeminars();
      } else if (getUserType() == UserType.LECTURER) {
        lecturer = getLecturerFromSession();
        seminarList = lecturer.getSeminars();
      }
    } catch (NotLoggedInException e) {
      // if there should be a user in the session according to its state but cant be fetched, flush session
      logOutUser();
      throw new GenericErrorException();
    }
    return SUCCESS;
  }
}
