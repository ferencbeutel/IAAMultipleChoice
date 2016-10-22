package de.nordakademie.multiplechoice.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.multiplechoice.model.User;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Map;

/**
 * Created by ferencbeutel on 17.10.16.
 */
@Getter
@Setter
public class HomeAction extends BaseAction {
    private String name;

    public String execute() {
        name = "Dir";

        if (isUserLoggedIn()) {
            User user = (User)session.get("user");
            name = user.getName() + " " +  user.getSurName();
        }
        return SUCCESS;
    }
}
