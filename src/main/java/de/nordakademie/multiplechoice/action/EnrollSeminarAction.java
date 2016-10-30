package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.AlreadyEnrolledException;
import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.exception.InsufficientPermissionsException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.model.User;
import de.nordakademie.multiplechoice.model.UserType;
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

    @Setter
    private long seminarId;

    @Getter
    Seminar seminar;

    public String enroll() throws NotLoggedInException, InsufficientPermissionsException, GenericErrorException, AlreadyEnrolledException {
        if(!isUserLoggedIn()) {
            throw new NotLoggedInException();
        }
        if(getUserType() != UserType.STUDENT) {
            throw new InsufficientPermissionsException();
        }
        Student student = getStudentFromSession();
        seminar = seminarService.byId(seminarId);
        if (seminar == null) {
            throw new GenericErrorException();
        }
        Set<Student> newParticipantsSet = seminar.getParticipants();
        if (newParticipantsSet.contains(student)) {
            throw new AlreadyEnrolledException();
        }
        newParticipantsSet.add(student);
        seminar.setParticipants(newParticipantsSet);
        seminarService.createOrUpdate(seminar);

        return SUCCESS;
    }
}
