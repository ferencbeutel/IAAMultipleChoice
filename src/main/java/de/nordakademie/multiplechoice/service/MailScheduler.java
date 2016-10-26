package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.model.Test;
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

    @Scheduled(cron = "0 12 * * *")
    public void sendTestToken() {
        for (Seminar seminar : seminarService.allWithTestsStartingToday()) {
            for(Student student : seminar.getParticipants()) {
                String mailText = "";
                try {
                    mailService.sendMail(student.getUser().getEmail(), "Your test in " + seminar.getName(), mailText);
                } catch (MessagingException e) {
                    //TODO: Implement Logging
                }
            }
        }
    }
}
