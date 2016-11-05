package de.nordakademie.multiplechoice.action;


import de.nordakademie.multiplechoice.exception.GenericErrorException;

import de.nordakademie.multiplechoice.service.*;


import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by Hendrik on 01.11.2016.
 */
public class AdministrateApplicationAction extends BaseAction {

    @Autowired
    private ExampleDataService exampleDataService;

    @Autowired
    private MailScheduler mailScheduler;

    @Setter
    private Integer quantStudents;

    @Setter
    private Integer quantLecturer;

    @Setter
    private Integer quantSeminars;

    public String initialize() {
        exampleDataService.generateTestData(quantStudents, quantSeminars, quantLecturer);

        return SUCCESS;
    }

    public String sendTestToken() throws GenericErrorException {
        mailScheduler.sendTestToken();

        return SUCCESS;
    }
}



