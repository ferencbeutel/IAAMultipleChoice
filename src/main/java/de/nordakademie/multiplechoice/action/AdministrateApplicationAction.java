package de.nordakademie.multiplechoice.action;


import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.model.Lecturer;
import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.service.ExampleDataService;
import de.nordakademie.multiplechoice.service.LecturerService;
import de.nordakademie.multiplechoice.service.MailScheduler;
import de.nordakademie.multiplechoice.service.StudentService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * This class is responsible for administrative tasks and to prepare test data
 *
 * @author Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */

public class AdministrateApplicationAction extends BaseAction {

  @Autowired
  private ExampleDataService exampleDataService;

  @Autowired
  private MailScheduler mailScheduler;

  @Autowired
  private StudentService studentService;

  @Autowired
  private LecturerService lecturerService;

  @Setter
  private Integer quantStudents;

  @Setter
  private Integer quantLecturer;

  @Setter
  private Integer quantSeminars;

  @Getter
  private boolean dbState;

  /**
   * This method prepares the input form for the application-administration page
   *
   * @return a String  which is used to select a result element in struts
   */
  public String openForm() {
    List<Student> students = studentService.findAll();
    List<Lecturer> lecturers = lecturerService.findAll();
    dbState = students.isEmpty() && lecturers.isEmpty();
    return SUCCESS;
  }

  /**
   * This method calls the generateTestData method in the exampleDataService in order to create some test-data
   *
   * @return a String  which is used to select a result element in struts
   */
  public String initialize() {
    exampleDataService.generateTestData(quantStudents, quantSeminars, quantLecturer);
    return SUCCESS;
  }

  /**
   * This method calls the sendTestToken method in mailScheduler in order to send the test tokens manually
   *
   * @return a String  which is used to select a result element in struts
   *
   * @throws GenericErrorException
   */
  public String sendTestToken() throws GenericErrorException {
    mailScheduler.sendTestToken();
    return SUCCESS;
  }
}



