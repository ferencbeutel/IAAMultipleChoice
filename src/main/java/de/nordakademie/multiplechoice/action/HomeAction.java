package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.*;
import de.nordakademie.multiplechoice.service.LecturerService;
import de.nordakademie.multiplechoice.service.StudentService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ferencbeutel on 17.10.16.
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

    public String execute() throws GenericErrorException {
        if (!isUserLoggedIn()) {
            return SUCCESS;
        }
        try {
            if(getUserType() == UserType.STUDENT) {
                student = getStudentFromSession();
                seminarList = student.getSeminars();
            } else if(getUserType() == UserType.LECTURER) {
                lecturer = getLecturerFromSession();
                seminarList = lecturer.getSeminars();
            }
        } catch(NotLoggedInException e) {
            // if we cant fetch a user from the session for some reason, flush it and display generic error
            logOutUser();
            throw new GenericErrorException();
        }
        return SUCCESS;
    }
}
