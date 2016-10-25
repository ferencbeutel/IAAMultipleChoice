package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.service.SeminarService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Created by ferencbeutel on 25.10.16.
 */
public class EnrollSeminarAction extends BaseAction {

    @Autowired
    SeminarService seminarService;

    @Setter
    private long seminarId;

    @Getter
    Seminar seminar;

    public String enroll() {
        if(!isUserLoggedIn()) {
            return "notLoggedInError";
        }
        Object userObject = session.get("user");
        if(!isStudent(userObject)) {
            return "insufficientPermissionsError";
        }
        Student student = (Student) userObject;
        seminar = seminarService.byId(seminarId);
        if(seminar == null) {
            return "seminarNotFoundError";
        }
        Set<Student> newParticipantsSet = seminar.getParticipants();
        if(newParticipantsSet.contains(student)) {
            return "alreadyEnrolledError";
        }
        newParticipantsSet.add(student);
        seminar.setParticipants(newParticipantsSet);
        seminarService.updateSeminar(seminar);

        return SUCCESS;
    }
}
