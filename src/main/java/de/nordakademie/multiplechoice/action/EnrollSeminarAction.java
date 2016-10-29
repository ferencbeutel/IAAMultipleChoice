package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.AlreadyEnrolledException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.model.User;
import de.nordakademie.multiplechoice.service.SeminarService;
import de.nordakademie.multiplechoice.service.StudentService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Created by ferencbeutel on 25.10.16.
 */
public class EnrollSeminarAction extends BaseAction {

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private StudentService studentService;

    @Setter
    private long seminarId;

    @Getter
    Seminar seminar;

    public String enroll() throws NotLoggedInException {
        User user = getUserFromSession();
        if (!isUserStudent(user)) {
            return "insufficientPermissions";
        }
        Student student = studentService.byUserId(user.getId());
        seminar = seminarService.byId(seminarId);
        if (seminar == null) {
            return "seminarNotFound";
        }
        Set<Student> newParticipantsSet = seminar.getParticipants();
        if (newParticipantsSet.contains(student)) {
            return "alreadyEnrolled";
        }
        newParticipantsSet.add(student);
        seminar.setParticipants(newParticipantsSet);
        seminarService.createOrUpdate(seminar);

        return SUCCESS;
    }
}
