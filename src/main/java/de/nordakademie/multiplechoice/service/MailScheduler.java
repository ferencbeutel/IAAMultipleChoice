package de.nordakademie.multiplechoice.service;


import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.model.TestResult;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ferencbeutel on 26.10.16.
 */
@Service
public class MailScheduler {

    @Autowired
    private MailService mailService;

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private UUIDService uuidService;

    @Scheduled(cron = "0 0 1 * * *")
    public void sendTestToken() throws GenericErrorException {
        for (Seminar seminar : seminarService.allWithTestsStartingToday()) {
            for (Student student : seminar.getParticipants()) {
                List<TestResult> testResultIntersection = new ArrayList<>(CollectionUtils.intersection(seminar.getTest().getResults(), student.getResults()));
                // There should be no test result which the student AND the test contains in their results yet
                if (testResultIntersection.size() != 0) {
                    //TODO: throw exception
                    System.out.println("oh noes! not again...");
                    return;
                }
                // Generate test Result and set access token
                String accessToken = uuidService.getUUID();
                TestResult testResult = new TestResult();
                testResult.setAccessToken(accessToken);

                // Persist test Result at student
                student.getResults().add(testResult);
                studentService.save(student);

                // Re-fetch the student to get the auto generated test result id
                student = studentService.findById(student.getUserId());

                // There should be exactly one TestResult which is not in the student AND the test(the one we just persisted)
                List<TestResult> testResultDisjunction = new ArrayList<>(CollectionUtils.disjunction(student.getResults(), seminar.getTest().getResults()));
                if (testResultDisjunction.size() != 1) {
                    //TODO: throw ex
                    System.out.println("oh noes #2...");
                    return;
                }

                //Persist test result obtained from student at test
                seminar.getTest().getResults().add(testResultDisjunction.get(0));
                seminarService.createOrUpdate(seminar);

                String mailText = "Your token for accessing the test: " + accessToken;
                try {
                    mailService.sendMail(student.getEmail(), "Your test in " + seminar.getName(), mailText);
                } catch (MessagingException e) {
                    //TODO: Implement Logging
                }
            }
        }
    }
}
