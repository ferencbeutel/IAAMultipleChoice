package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.model.*;
import de.nordakademie.multiplechoice.service.QuestionService;
import de.nordakademie.multiplechoice.service.SeminarService;
import de.nordakademie.multiplechoice.service.StudentService;
import de.nordakademie.multiplechoice.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Ferenc on 19.10.2016.
 */
public class AddSeminarAction extends BaseAction {
    @Autowired
    SeminarService seminarService;

    @Getter
    @Setter
    private Seminar seminar;

    @Setter
    private String beginDate;

    @Setter
    private String endDate;

    public String openForm() {
        if (!isUserLoggedIn()) {
            return "notLoggedInError";
        } else {
            Object userObject = session.get("user");
            try {
                Lecturer lecturer = (Lecturer) userObject;
            } catch (ClassCastException e) {
                return "insufficientPermissionError";
            }
        }

        return SUCCESS;
    }

    public String addSeminar() {
        //TODO: Find a way to bind dates directly to the seminar model
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        seminar.setBeginDate(LocalDate.parse(beginDate, formatter));
        seminar.setEndDate(LocalDate.parse(endDate, formatter));
        Lecturer lecturer = (Lecturer)session.get("user");
        seminar.setLecturer(lecturer);
        seminarService.saveSeminar(seminar);
        return SUCCESS;
    }
}
