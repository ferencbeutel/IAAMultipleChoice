package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.exception.InsufficientPermissionsException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.*;
import de.nordakademie.multiplechoice.service.SeminarService;
import de.nordakademie.multiplechoice.util.DateTimeValidationUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

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
        if (getUserType() != UserType.LECTURER) {
            throw new InsufficientPermissionsException();
        }
        Seminar seminar = seminarService.byId(seminarId);
        System.out.println(seminar.getTest());
        if (seminar.getTest().getQuestions() != null){
            Test testOld = seminar.getTest();
            test.setQuestions(testOld.getQuestions());
            }

        test.setBeginDate(LocalDate.parse(startDateString, dateFormatter));
        test.setEndDate(LocalDate.parse(endDateString, dateFormatter));
        test.setDuration(LocalTime.parse(durationString, durationFormatter));

        seminar.setTest(test);
        seminarService.createOrUpdate(seminar);
        return SUCCESS;
    }

    public void validate() {
        boolean startDateParseable = false;
        if(!DateTimeValidationUtils.isDateParseable(startDateString, dateFormatter)) {
            addFieldError("startDate", getI18NValue("persistTestFieldError.validStart"));
        } else {
            startDateParseable = true;
        }
        boolean endDateParseable = false;
        if(!DateTimeValidationUtils.isDateParseable(endDateString, dateFormatter)) {
            addFieldError("endDateString", getI18NValue("persistTestFieldError.validEnd"));
        } else {
            endDateParseable = true;
        }

        if(endDateParseable && startDateParseable) {
            LocalDate startDate = LocalDate.parse(startDateString, dateFormatter);
            LocalDate endDate = LocalDate.parse(endDateString, dateFormatter);
            LocalDate now = LocalDate.now();
            if(startDate.isBefore(now.plusDays(1))) {
                addFieldError("startDate", getI18NValue("persistTestFieldError.startInPast"));
            }
            if(endDate.isBefore(startDate)) {
                addFieldError("endDateString", getI18NValue("persistTestFieldError.startBeforeEnd"));
            }
        }
        Seminar seminar = seminarService.byId(seminarId);
        if(LocalDate.parse(startDateString, dateFormatter).isBefore(seminar.getBeginDate())){
            addFieldError("startDate", getI18NValue("persistTestFieldError.seminarStart"));
        }
        if(!DateTimeValidationUtils.isTimeParseable(durationString, durationFormatter)) {
            addFieldError("duration", getI18NValue("persistTestFieldError.duration"));
        }

        if(test.getPassingThreshold() <= 0) {
            addFieldError("passingThreshold", getI18NValue("persistTestFieldError.passingThreshold"));
        }
    }
}

