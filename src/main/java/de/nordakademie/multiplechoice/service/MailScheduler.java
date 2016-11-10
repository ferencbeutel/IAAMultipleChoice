package de.nordakademie.multiplechoice.service;


import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.model.TestResult;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class is used to generate mails with test tokens
 * Mails will be send out, when a test starts
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
@Service
public class MailScheduler {
    /**
     * Mail service that is used to send mails
     */
    @Autowired
    private MailService mailService;
    /**
     * Service to manage testresults
     */
    @Autowired
    private TestResultService testResultService;

    /**
     * Service to manage seminars
     */
    @Autowired
    private SeminarService seminarService;
    /**
     * Service to manage students
     */
    @Autowired
    private StudentService studentService;
    /**
     * Creation of unique ids
     */
    @Autowired
    private UUIDService uuidService;

    /**
     * This method is used to send test token via mail
     * @throws GenericErrorException
     */
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
                // Creation of mail
                HttpServletRequest request = ServletActionContext.getRequest();
                Locale userLocale = request.getLocale();
                ResourceBundle messages = ResourceBundle.getBundle("messages", userLocale);
                String mailText = "<p>"+StringEscapeUtils.unescapeHtml4(messages.getString("tokenMail.opening")) + " " + student.getName() + " " + student.getSurName() + ",</p>";
                mailText = mailText + StringEscapeUtils.unescapeHtml4(messages.getString("tokenMail.token"));
                mailText = mailText + "<p>" + accessToken + "</p>";
                mailText = mailText + StringEscapeUtils.unescapeHtml4(messages.getString("registrationMail.textGreeting"));
                mailText = mailText + StringEscapeUtils.unescapeHtml4(messages.getString("registrationMail.textSignature"));
                try {
                    mailService.sendMail(student.getEmail(), StringEscapeUtils.unescapeHtml4(messages.getString("tokenMail.tokenTest")) + " " + seminar.getName(), mailText);
                } catch (MessagingException e) {
                    System.out.println("Gmail sucks :/");
                    //TODO: Implement Logging
                }
            }
        }
    }
}
