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
 * This class is responsible for persisting a created seminar
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
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

    /**
     * This method persists a created seminar
     * @return  a String  which is used to select a result element in struts
     * @throws NotLoggedInException
     * @throws InsufficientPermissionsException
     * @throws GenericErrorException
     */
    public String persistSeminar() throws NotLoggedInException, InsufficientPermissionsException, GenericErrorException {
        if(getUserType() != UserType.LECTURER) {
            throw new InsufficientPermissionsException();
        }
        Lecturer lecturer = getLecturerFromSession();

        if(seminarService.byId(seminarId)!= null){
        Seminar seminarOld = seminarService.byId(seminarId);
        seminar.setTest(seminarOld.getTest());
        seminar.setParticipants(seminarOld.getParticipants());
        }

        seminar.setSeminarId(seminarId);
        seminar.setBeginDate(LocalDate.parse(beginDateString, dateFormatter));
        seminar.setEndDate(LocalDate.parse(endDateString, dateFormatter));
        seminar.setLecturer(lecturer);
        seminarService.createOrUpdate(seminar);
        return SUCCESS;
    }

    /**
     * This method validates the inputs of the add-seminar-page
     */
    public void validate() {
        boolean startDateParseable = false;
        if (!DateTimeValidationUtils.isDateParseable(beginDateString, dateFormatter)) {
            addFieldError("startDate", getI18NValue("persistSeminarFieldError.validStart"));
        } else {
            startDateParseable = true;
        }

        boolean endDateParseable = false;
        if (!DateTimeValidationUtils.isDateParseable(endDateString, dateFormatter)) {
            addFieldError("endDateString", getI18NValue("persistSeminarFieldError.validEnd"));
        } else {
            endDateParseable = true;
        }

        if (endDateParseable && startDateParseable) {
            LocalDate startDate = LocalDate.parse(beginDateString, dateFormatter);
            LocalDate endDate = LocalDate.parse(endDateString, dateFormatter);
            LocalDate now = LocalDate.now();
            if (startDate.isBefore(now)) {
                addFieldError("startDate", getI18NValue("persistSeminarFieldError.startInPast"));
            }
            if (endDate.isBefore(startDate)) {
                addFieldError("endDateString", getI18NValue("persistSeminarFieldError.startBeforeEnd"));
            }
        }

        if (seminar.getDescription() == null || seminar.getDescription().length() < 2) {
            addFieldError("description", getI18NValue("persistSeminarFieldError.description"));
        }
        if (seminar.getMaxParticipants() <= 0) {
            addFieldError("maxParticipants", getI18NValue("persistSeminarFieldError.maxParticipants"));
        }
        if (seminar.getName() == null || seminar.getName().length() < 2) {
            addFieldError("name", getI18NValue("persistSeminarFieldError.name"));
        }
    }


}
