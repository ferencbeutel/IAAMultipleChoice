package de.nordakademie.multiplechoice.action;

import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by ferencbeutel on 17.10.16.
 */
//testkommentar2
@Getter
@Setter
public class HomeAction extends ActionSupport {
    private String name;

    public String execute() {
        name = "Ferenc";
        return SUCCESS;
    }
}
