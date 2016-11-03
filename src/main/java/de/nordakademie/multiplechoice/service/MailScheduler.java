package de.nordakademie.multiplechoice.service;


import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.model.TestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

/**
 * Created by ferencbeutel on 26.10.16.
 */
@Service
public class MailScheduler {

    @Autowired
    private MailService mailService;

    @Autowired
    private TestResultService testResultService;

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
                // Re-Fetch the Student since his test results could have been updated already
                student = studentService.findById(student.getUserId());

                String accessToken = uuidService.getUUID();
                TestResult testResult = new TestResult();
                testResult.setAccessToken(accessToken);
                testResult = testResultService.createOrUpdate(testResult);

                student.getResults().add(testResult);
                studentService.createOrUpdate(student);

                seminar.getTest().getResults().add(testResult);
                seminar = seminarService.createOrUpdate(seminar);

                String mailText = "Your token for accessing the test: " + accessToken;
                try {
                    mailService.sendMail(student.getEmail(), "Your test in " + seminar.getName(), mailText);
                } catch (MessagingException e) {
                    System.out.println("Gmail sucks :/");
                    //TODO: Implement Logging
                }
            }
        }
    }
}
