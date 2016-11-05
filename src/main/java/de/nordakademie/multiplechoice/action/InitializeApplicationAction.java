package de.nordakademie.multiplechoice.action;


import de.nordakademie.multiplechoice.model.*;

import de.nordakademie.multiplechoice.service.*;


import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Hendrik on 01.11.2016.
 */
public class InitializeApplicationAction extends BaseAction {

    @Autowired
    private ExampleDataService exampleDataService;

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
}



