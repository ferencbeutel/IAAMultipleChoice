package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.model.CreditPointsType;
import de.nordakademie.multiplechoice.model.EvaluationType;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.Test;
import de.nordakademie.multiplechoice.service.SeminarService;
import de.nordakademie.multiplechoice.service.TestService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Hendrik Peters on 17.10.16.
 */
public class AddTestAction extends BaseAction {
    @Autowired
    private TestService testService;

    @Getter
    @Setter
    private Test test;

    @Getter
    @Setter
    private String seminarName;

    @Setter
    private String startDate;
    @Setter
    private String endDate;
    @Setter
    private String duration;

    public String addTest() {
        //TODO: Find a way to bind dates directly to the test model
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("H:mm:ss");
        test.setBeginDate(LocalDate.parse(startDate, formatter));
        test.setEndDate(LocalDate.parse(endDate, formatter));
        test.setDuration(LocalTime.parse(duration, formatterDate));
        testService.saveTest(test);
        return SUCCESS;
    }

    public void validate() {
        if (startDate == null || !startDate.matches("[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}")) {
            addFieldError("startDate", "Please enter a valid begin date");
        }
        if (endDate == null || !endDate.matches("[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}")) {
            addFieldError("endDate", "Please enter a valid end date");
        }
        if (startDate.matches("[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}") && endDate.matches("[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}")) {
            if (endDate.compareTo(startDate) < 0) {
                addFieldError("endDate", "Please enter an end date following the start date");
            }
        }
        if (startDate.matches("[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate begin = LocalDate.parse(startDate, formatter);
            if (begin.compareTo(LocalDate.now().minusDays(1)) <= 0) {
                addFieldError("beginDate", "Please enter a begin date that is not in the past");
            }
        }
        if (duration == null) {
            addFieldError("duration", "Please enter a duration for the test");
        } else if (duration != null && !duration.matches("[0-9]{2}:[0-9]{2}:[0-9]{2}")) {
            addFieldError("duration", "Please enter a duration in the format hh:mm:ss");
        }
        if (test.getMinScore() <= 0) {
            addFieldError("minScore", "Please enter a minimum score greater than 0");
        }
    }
}

