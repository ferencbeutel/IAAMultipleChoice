package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.Lecturer;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.model.User;
import de.nordakademie.multiplechoice.service.LecturerService;
import de.nordakademie.multiplechoice.service.StudentService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ferencbeutel on 17.10.16.
 */
@Getter
@Setter
public class HomeAction extends BaseAction {
    private String name;

    @Autowired
    private StudentService studentService;

    @Autowired
    private LecturerService lecturerService;

    @Getter
    private Set<Seminar> seminarList = new HashSet<>();

    //TODO: Overthink current student/lecturer implementation since it leads to duplicate code
    public String execute() throws GenericErrorException {
        name = "Dir";
        try {
            User user = getUserFromSession();
            name = user.getName() + " " + user.getSurName();
            if(isUserStudent(user)) {
                Student student = studentService.byUserId(user.getId());
                seminarList = student.getSeminars();
            } else if (isUserLecturer(user)) {
                Lecturer lecturer = lecturerService.byUserId(user.getId());
                seminarList = lecturer.getSeminars();
            } else {
                throw new GenericErrorException();
            }
        } catch(NotLoggedInException e) {
            //this is fine...
        }
        return SUCCESS;
    }
}
