package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.model.Test;
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
    private SeminarService seminarService;

    @Autowired
    private UUIDService uuidService;

    @Scheduled(cron = "0 29 13 * * *")
    public void sendTestToken() {
        for (Seminar seminar : seminarService.allWithTestsStartingToday()) {
            for(Student student : seminar.getParticipants()) {
                String accessToken = uuidService.getUUID();
                TestResult testResult = new TestResult();
                testResult.setAccessToken(accessToken);
                student.getResults().add(testResult);
                seminar.getTest().getResults().add(testResult);
                String mailText = "Your token for accessing the test: " + accessToken;
                try {
                    mailService.sendMail(student.getUser().getEmail(), "Your test in " + seminar.getName(), mailText);
                } catch (MessagingException e) {
                    //TODO: Implement Logging
                }
            }
        }
    }
}
