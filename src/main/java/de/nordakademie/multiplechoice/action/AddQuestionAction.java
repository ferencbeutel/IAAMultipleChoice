package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.model.*;
import de.nordakademie.multiplechoice.service.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ferenc on 19.10.2016.
 */
public class AddQuestionAction extends BaseAction {

    @Autowired
    QuestionService questionService;
    @Autowired
    AnswerService answerService;
    @Getter
    @Setter
    private Question question;
    @Getter
    private List<String> questionTypes;

    public String addQuestion(){
        questionService.saveQuestion(question);
        return SUCCESS;
    }

    public String prepare(){
        questionTypes = new ArrayList<String>();
        for (QuestionType questionType: QuestionType.values()) {
            questionTypes.add(questionType.name());
        }
        return SUCCESS;
    }


}
