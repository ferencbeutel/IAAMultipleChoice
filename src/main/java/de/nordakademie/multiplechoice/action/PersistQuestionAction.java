package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.InsufficientPermissionsException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.*;
import de.nordakademie.multiplechoice.service.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Ferenc on 19.10.2016.
 */
public class PersistQuestionAction extends BaseAction {

    @Autowired
    private SeminarService seminarService;

    @Setter
    private List<String> singleChoiceAnswers;

    @Setter
    private List<Integer> singleChoiceAnswerValues;

    @Setter
    private List<String> multipleChoiceAnswers;

    @Setter
    private List<Integer> multipleChoiceAnswerValues;

    @Setter
    private List<String> gapAnswers;

    @Getter
    @Setter
    private Question question;

    @Getter
    @Setter
    private long seminarId;

    @Getter
    @Setter
    private long questionId;

    public String persist() throws NotLoggedInException, InsufficientPermissionsException {
        if (getUserType() != UserType.LECTURER) {
            throw new InsufficientPermissionsException();
        }
        Seminar seminar = seminarService.byId(seminarId);
        Test test = seminar.getTest();
        question.setQuestionId(questionId);
        question.setAnswers(new ArrayList<>());
        if (question.getPosition() == 0) {
            question.setPosition(test.getQuestions().size());
        }
        List<String> answersToPersist = new ArrayList<>();
        List<Integer> answerValuesToPersist = new ArrayList<>();
        switch (question.getType()) {
            case Single:
                answersToPersist = singleChoiceAnswers;
                answerValuesToPersist = singleChoiceAnswerValues;
                break;
            case Multiple:
                answersToPersist = multipleChoiceAnswers;
                answerValuesToPersist = multipleChoiceAnswerValues;
                break;
            case Gap:
                answersToPersist = gapAnswers;
                for (int i = 0; i <= answersToPersist.size(); i++) {
                    answerValuesToPersist.add(i);
                }
                break;
        }

        int i = 0;
        for (String answerString : answersToPersist) {
            Answer answer = new Answer();
            answer.setText(answerString);
            answer.setPosition(i);
            if (answerValuesToPersist.contains(i)) {
                answer.setCorrect(true);
            } else {
                answer.setCorrect(false);
            }
            question.getAnswers().add(answer);
            i++;
        }

        test.setQuestions(
                test.getQuestions().stream()
                        .filter(question -> question.getQuestionId() != questionId)
                        .collect(Collectors.toList())
        );
        test.getQuestions().add(question);
        seminarService.createOrUpdate(seminar);
        return SUCCESS;
    }


    public void validate() {
        try {
            switch (question.getType()) {
                case Single:
                    for (int i = 0; i < singleChoiceAnswers.size(); i++) {
                        if (singleChoiceAnswers.get(i).length() < 1) {
                            addFieldError("text", getI18NValue("questionFieldError.answers"));
                            break;
                        }
                    }
                    if (singleChoiceAnswerValues == null) {
                        addFieldError("text", getI18NValue("questionFieldError.correctAnswers"));
                    }
                    break;
                case Multiple:
                    for (int i = 0; i < multipleChoiceAnswers.size(); i++) {
                        if (multipleChoiceAnswers.get(i).length() < 1) {
                            addFieldError("text", getI18NValue("questionFieldError.answers"));
                            break;
                        }
                    }
                    if (multipleChoiceAnswerValues == null) {
                        addFieldError("text", getI18NValue("questionFieldError.correctAnswers"));
                    }
                    break;
                case Gap:
                    if (gapAnswers == null) {
                        addFieldError("text", getI18NValue("questionFieldError.gapAnswers"));
                    } else {
                        for (int i = 0; i < gapAnswers.size(); i++) {
                            if (gapAnswers.get(i).length() < 1) {
                                addFieldError("text", getI18NValue("questionFieldError.answers"));
                                break;
                            }
                        }
                    }
                    break;
            }

            if (question.getPoints() <= 0) {
                addFieldError("points", getI18NValue("questionFieldError.points"));
            }
            if (question.getText() == null || question.getText().length() <= 5) {
                addFieldError("text", getI18NValue("questionFieldError.texts"));
            }
        } catch (NullPointerException e) {
            addFieldError("type", getI18NValue("questionFieldError.questionType"));
        }
    }
}
