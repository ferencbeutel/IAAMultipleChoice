package de.nordakademie.multiplechoice.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.multiplechoice.model.Lecturer;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.model.User;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ferencbeutel on 17.10.16.
 */
@Getter
@Setter
public class HomeAction extends BaseAction {
    private String name;

    @Getter
    private Set<Seminar> seminarList = new HashSet<>();

    //TODO: Overthink current student/lecturer implementation since it leads to duplicate code
    public String execute() {
        name = "Dir";

        if (isUserLoggedIn()) {
            Object userObject = session.get("user");
            Student student = null;
            Lecturer lecturer = null;
            try {
                student = (Student) userObject;
            } catch (ClassCastException studentEx) {
                try {
                    lecturer = (Lecturer) userObject;
                } catch (ClassCastException lecturerEx) {
                    return "illegalSessionStateError";
                }
            }
            if(student == null) {
                User user = lecturer.getUser();
                name = user.getName() + " " + user.getSurName();
                seminarList = lecturer.getSeminars();
            } else {
                User user = student.getUser();
                name = user.getName() + " " + user.getSurName();
                seminarList = student.getSeminars();
            }

        }
        return SUCCESS;
    }
}
