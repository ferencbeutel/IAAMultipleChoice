package de.nordakademie.multiplechoice.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.multiplechoice.model.Lecturer;
import de.nordakademie.multiplechoice.model.Test;
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
    TestService testService;

    @Getter
    @Setter
    private Test test;

    @Setter
    private String startDate;

    @Setter
    private String endDate;

    @Setter
    private String duration;

    public String execute() {
        return SUCCESS;
    }

    public String openForm() {
        if (!isUserLoggedIn()) {
            return "notLoggedInError";
        } else {
            Object userObject = session.get("user");
            if(!isLecturer(userObject)) {
                return "insufficientPermissionError";
            }
        }
        return SUCCESS;
    }
    public String addTest() {
        //TODO: Find a way to bind dates directly to the test model
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("H:mm:ss");
        test.setBeginDate(LocalDate.parse( startDate, formatter));
        test.setEndDate(LocalDate.parse(endDate, formatter));
        test.setDuration(LocalTime.parse(duration, formatterDate));
        testService.saveTest(test);
        return SUCCESS;
    }
}

