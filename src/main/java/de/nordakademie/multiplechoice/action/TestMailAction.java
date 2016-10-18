package de.nordakademie.multiplechoice.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.multiplechoice.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Ferenc on 18.10.2016.
 */
public class TestMailAction extends ActionSupport {
    @Autowired
    MailService mailService;

    public void sendMail() {
        mailService.sendMail("melanie.beckmann@nordakademie.de, ferenc.beutel@nordakademie.de, max.hort@nordakademie.de, hendrik.peters@nordakademie.de", "This is just a test", "Wow, this is simply amazing!");
    }
}
