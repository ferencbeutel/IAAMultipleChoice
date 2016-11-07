package de.nordakademie.multiplechoice.action;


import de.nordakademie.multiplechoice.exception.GenericErrorException;

import de.nordakademie.multiplechoice.model.Lecturer;
import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.service.*;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Created by Hendrik on 01.11.2016.
 */
public class AdministrateApplicationAction extends BaseAction {

    @Autowired
    private ExampleDataService exampleDataService;

    @Autowired
    private MailScheduler mailScheduler;

    @Setter
    private Integer quantStudents;

    @Setter
    private Integer quantLecturer;

    @Setter
    private Integer quantSeminars;

    @Getter
    private boolean dbState;

    @Autowired
    private StudentService studentService;

    @Autowired
    private LecturerService lecturerService;

    public String openForm() {
        List<Student> students = studentService.findAll();
        List<Lecturer> lecturers = lecturerService.findAll();
        dbState = students.isEmpty() && lecturers.isEmpty();
        return SUCCESS;
    }
    public String initialize() {
        exampleDataService.generateTestData(quantStudents, quantSeminars, quantLecturer);

        return SUCCESS;
    }

    public String sendTestToken() throws GenericErrorException {
        mailScheduler.sendTestToken();

        return SUCCESS;
    }
}



