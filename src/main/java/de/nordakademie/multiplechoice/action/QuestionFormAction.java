package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.GenericErrorException;
import de.nordakademie.multiplechoice.exception.InsufficientPermissionsException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.Question;
import de.nordakademie.multiplechoice.model.QuestionType;
import de.nordakademie.multiplechoice.model.User;
import de.nordakademie.multiplechoice.model.UserType;
import de.nordakademie.multiplechoice.service.QuestionService;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class is responsible for preparing the forms for adding and editing of questions
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
public class QuestionFormAction extends BaseAction {

    @Autowired
    private QuestionService questionService;

    @Getter
    @Setter
    private long seminarId;

    @Getter
    @Setter
    private long questionId;

    @Getter
    @Setter
    private Question question;

    /**
     * This method prepares the input form for the add question page
     * @return a String  which is used to select a result element in struts
     * @throws NotLoggedInException
     * @throws InsufficientPermissionsException
     */
    public String openForm() throws NotLoggedInException, InsufficientPermissionsException {
        if (getUserType() != UserType.LECTURER) {
            throw new InsufficientPermissionsException();
        }

        return SUCCESS;
    }

    /**
     * This method prepares edit-form for questions
     * @return a String  which is used to select a result element in struts
     * @throws NotLoggedInException
     * @throws InsufficientPermissionsException
     */
    public String openEditForm() throws NotLoggedInException, InsufficientPermissionsException {
        if (getUserType() != UserType.LECTURER) {
            throw new InsufficientPermissionsException();
        }

        question = questionService.byId(questionId);

        return SUCCESS;
    }
}


