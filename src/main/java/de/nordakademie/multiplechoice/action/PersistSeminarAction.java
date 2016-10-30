package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.exception.InsufficientPermissionsException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.*;
import de.nordakademie.multiplechoice.service.*;
import de.nordakademie.multiplechoice.util.DateTimeValidationUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Ferenc on 19.10.2016.
 */
public class PersistSeminarAction extends BaseAction {
    @Autowired
    private SeminarService seminarService;

    @Autowired
    private LecturerService lecturerService;

    @Getter
    @Setter
    private Seminar seminar;

    @Setter
    private String beginDateString;

    @Setter
    private String endDateString;

    @Setter
    @Getter
    private long seminarId;

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public String persistSeminar() throws NotLoggedInException, InsufficientPermissionsException, GenericErrorException {
        if(getUserType() != UserType.LECTURER) {
            throw new InsufficientPermissionsException();
        }
        Lecturer lecturer = getLecturerFromSession();

        seminar.setSeminarId(seminarId);
        seminar.setBeginDate(LocalDate.parse(beginDateString, dateFormatter));
        seminar.setEndDate(LocalDate.parse(endDateString, dateFormatter));
        seminar.setLecturer(lecturer);
        seminarService.createOrUpdate(seminar);
        return SUCCESS;
    }

    public void validate() {
        boolean startDateParseable = false;
        if (!DateTimeValidationUtils.isDateParseable(beginDateString, dateFormatter)) {
            addFieldError("startDate", "Please enter a valid begin date");
        } else {
            startDateParseable = true;
        }

        boolean endDateParseable = false;
        if (!DateTimeValidationUtils.isDateParseable(endDateString, dateFormatter)) {
            addFieldError("endDateString", "Please enter a valid end date");
        } else {
            endDateParseable = true;
        }

        if (endDateParseable && startDateParseable) {
            LocalDate startDate = LocalDate.parse(beginDateString, dateFormatter);
            LocalDate endDate = LocalDate.parse(endDateString, dateFormatter);
            LocalDate now = LocalDate.now();
            if (startDate.isBefore(now)) {
                addFieldError("startDate", "Please enter a start date which is not in the past");
            }
            if (endDate.isBefore(startDate)) {
                addFieldError("endDateString", "Please enter an end date which is after the start date");
            }
        }

        if (seminar.getDescription() == null || seminar.getDescription().length() < 2) {
            addFieldError("description", "Please enter a description to the seminar");
        }
        if (seminar.getMaxParticipants() <= 0) {
            addFieldError("maxParticipants", "Please enter a maximum number of participants > 0");
        }
        if (seminar.getName() == null || seminar.getName().length() < 2) {
            addFieldError("name", "Please enter a name to the seminar");
        }
    }


}
