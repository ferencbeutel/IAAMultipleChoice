package de.nordakademie.multiplechoice.model;


import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class represents the evaluation type of a test
 * The evaluation type illustrates how wrong answers are being evaluated
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
public enum EvaluationType {
    SUBSTRACT("evaluationType.substract"),
    FATAL("evaluationType.fatal");

    /**
     * Key to access I18N text for EvaluationType
     */
    private final String i18nLookupKey;

    EvaluationType(final String s) {
        this.i18nLookupKey = s;
    }

    /**
     * This method is used to get the I18N value of an EvaluationType
     * @return I18N string for EvaluationType
     */
    public String getRealVal() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Locale userLocale = request.getLocale();
        ResourceBundle messages = ResourceBundle.getBundle("messages", userLocale);
        return StringEscapeUtils.unescapeHtml4(messages.getString(i18nLookupKey));
    }
}