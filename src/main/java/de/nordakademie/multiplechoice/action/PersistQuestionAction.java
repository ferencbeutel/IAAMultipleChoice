package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.exception.InsufficientPermissionsException;
import de.nordakademie.multiplechoice.exception.NotLoggedInException;
import de.nordakademie.multiplechoice.model.*;
import de.nordakademie.multiplechoice.service.SeminarService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is responsible for persisting a created question
 *
 * @author Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
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

  /**
   * This method persists a question in a test
   *
   * @return a String  which is used to select a result element in struts
   *
   * @throws NotLoggedInException
   * @throws InsufficientPermissionsException
   */

  //Todo more inline coding?
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
    //Save answer to question
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
    //Add questions to test
    test.setQuestions(test.getQuestions().stream().filter(question -> question.getQuestionId() != questionId)
                          .collect(Collectors.toList()));
    //Add questions to test
    test.getQuestions().add(question);
    //Save changes (new Questions) in Test at seminar
    seminarService.createOrUpdate(seminar);
    return SUCCESS;
  }

  /**
   * This method validates the inputs of the add-question page
   */
  public void validate() {
    try {
      switch (question.getType()) {
        case Single:
          for (int i = 0; i < singleChoiceAnswers.size(); i++) {
            //Checks whether the length of the answer is at least 1 character
            if (singleChoiceAnswers.get(i).length() < 1) {
              addFieldError("text", getI18NValue("questionFieldError.answers"));
              break;
            }
          }
          //Checks if there an answer for the question
          if (singleChoiceAnswerValues == null) {
            addFieldError("text", getI18NValue("questionFieldError.correctAnswers"));
          }
          break;
        case Multiple:
          for (int i = 0; i < multipleChoiceAnswers.size(); i++) {
            //Checks whether the length of the answer is at least 1 character
            if (multipleChoiceAnswers.get(i).length() < 1) {
              addFieldError("text", getI18NValue("questionFieldError.answers"));
              break;
            }
          }
          //Checks if there is an answer for the question
          if (multipleChoiceAnswerValues == null) {
            addFieldError("text", getI18NValue("questionFieldError.correctAnswers"));
          }
          break;
        case Gap:
          if (gapAnswers == null) {
            addFieldError("text", getI18NValue("questionFieldError.gapAnswers"));
          } else {
            for (int i = 0; i < gapAnswers.size(); i++) {
              //Checks whether the length of the answer is at least 1 character
              if (gapAnswers.get(i).length() < 1) {
                addFieldError("text", getI18NValue("questionFieldError.answers"));
                break;
              }
            }
          }
          break;
      }
      //Checks that the question has at least 1 point
      if (question.getPoints() <= 0) {
        addFieldError("points", getI18NValue("questionFieldError.points"));
      }
      //Checks that the question text is longer than 5 characters
      if (question.getText() == null || question.getText().length() <= 5) {
        addFieldError("text", getI18NValue("questionFieldError.texts"));
      }
    } catch (NullPointerException e) {
      addFieldError("type", getI18NValue("questionFieldError.questionType"));
    }
  }
}
