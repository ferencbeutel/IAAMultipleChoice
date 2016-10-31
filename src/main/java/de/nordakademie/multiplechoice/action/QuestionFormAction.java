package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.exception.InsufficientPermissionsException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.QuestionType;
import de.nordakademie.multiplechoice.model.User;
import de.nordakademie.multiplechoice.model.UserType;
import lombok.Getter;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Ferenc on 19.10.2016.
 */
public class QuestionFormAction extends BaseAction {
    private Locale aLocale;

    @Getter
    private List<String> questionTypes;

    public String openForm() throws NotLoggedInException, InsufficientPermissionsException, GenericErrorException {
        if(getUserType() != UserType.LECTURER) {
            throw new InsufficientPermissionsException();
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        Locale userLocale = request.getLocale();
        ResourceBundle messages = ResourceBundle.getBundle("messages", userLocale);
        questionTypes = new ArrayList<>();
        for (QuestionType questionType: QuestionType.values()) {
            questionTypes.add(messages.getString("question."+questionType.toString()));
        }
        return SUCCESS;
        }

    }


