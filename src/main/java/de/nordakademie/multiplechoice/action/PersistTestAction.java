package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.exception.InsufficientPermissionsException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.*;
import de.nordakademie.multiplechoice.service.SeminarService;
import de.nordakademie.multiplechoice.util.DateTimeValidationUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Hendrik Peters on 17.10.16.
 */
public class PersistTestAction extends BaseAction {
    @Autowired
    private SeminarService seminarService;

    @Getter
    @Setter
    private Test test;

    @Getter
    @Setter
    private long seminarId;

    @Setter
    private String startDateString;
    @Setter
    private String endDateString;
    @Setter
    private String durationString;

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter durationFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public String persistTest() throws NotLoggedInException, InsufficientPermissionsException, GenericErrorException {
        if(getUserType() != UserType.LECTURER) {
            throw new InsufficientPermissionsException();
        }
        test.setBeginDate(LocalDate.parse(startDateString, dateFormatter));
        test.setEndDate(LocalDate.parse(endDateString, dateFormatter));
        test.setDuration(LocalTime.parse(durationString, durationFormatter));
        Seminar seminar = seminarService.byId(seminarId);
        seminar.setTest(test);
        seminarService.createOrUpdate(seminar);
        return SUCCESS;
    }

    public void validate() {

        boolean startDateParseable = false;
        if(!DateTimeValidationUtils.isDateParseable(startDateString, dateFormatter)) {
            addFieldError("startDate", "Please enter a valid begin date");
        } else {
            startDateParseable = true;
        }

        boolean endDateParseable = false;
        if(!DateTimeValidationUtils.isDateParseable(endDateString, dateFormatter)) {
            addFieldError("endDateString", "Please enter a valid end date");
        } else {
            endDateParseable = true;
        }

        if(endDateParseable && startDateParseable) {
            LocalDate startDate = LocalDate.parse(startDateString, dateFormatter);
            LocalDate endDate = LocalDate.parse(endDateString, dateFormatter);
            LocalDate now = LocalDate.now();
            if(startDate.isBefore(now)) {
                addFieldError("startDate", "Please enter a start date which is not in the past");
            }
            if(endDate.isBefore(startDate)) {
                addFieldError("endDateString", "Please enter an end date which is after the start date");
            }
        }

        if(!DateTimeValidationUtils.isTimeParseable(durationString, durationFormatter)) {
            addFieldError("duration", "Please enter a valid duration");
        }

        if(test.getMinScore() <= 0) {
            addFieldError("minScore", "Please enter a minimum score greater than 0");
        }
    }
}

