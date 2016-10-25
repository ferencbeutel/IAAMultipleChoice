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

    public void validate() {
        if (beginDate == null || !beginDate.matches("[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}")){
            addFieldError("beginDate", "Please enter a valid begin date");
        }
        if (endDate == null || !endDate.matches("[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}")) {
            addFieldError("endDate", "Please enter a valid end date");
        }
        if (beginDate.matches("[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}") && endDate.matches("[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}")){
            if (endDate.compareTo(beginDate)<0){
                addFieldError("endDate", "Please enter an end date following the start date");
            }
        }
        if (seminar.getDescription()==null || seminar.getDescription().length()<2){
            addFieldError("description", "Please enter a descritption to the seminar");
        }
        if (seminar.getMaxParticipants()<=0){
            addFieldError("maxParticipants", "Please enter a maximum number of participiant > 0");
        }
        if (seminar.getName() == null || seminar.getName().length()<2){
            addFieldError("name", "Please enter a name to the seminar");
        }
    }



}
