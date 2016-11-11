package de.nordakademie.multiplechoice.model;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class represents the question type of a question
 * The evaluation type answers can be created for the question
 *
 * @author Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
public enum QuestionType {
  Single("questionType.single"),
  Multiple("questionType.multiple"),
  Gap("questionType.cloze");

  /**
   * Key to access I18N text for QuestionType
   */
  private final String i18nLookupKey;

  QuestionType(final String s) {
    this.i18nLookupKey = s;
  }

  /**
   * This method is used to get the I18N value of an QuestionType
   *
   * @return I18N string for QuestionType
   */
  public String getRealVal() {
    HttpServletRequest request = ServletActionContext.getRequest();
    Locale userLocale = request.getLocale();
    ResourceBundle messages = ResourceBundle.getBundle("messages", userLocale);

    return StringEscapeUtils.unescapeHtml4(messages.getString(i18nLookupKey));
  }
}