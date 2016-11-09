package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by MHORT on 24.10.2016.
 */
public enum EvaluationType {
    SUBSTRACT("evaluationType.substract"),
    FATAL("evaluationType.fatal");

    private final String i18nLookupKey;

    EvaluationType(final String s) {
        this.i18nLookupKey = s;
    }

    public String getRealVal() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Locale userLocale = request.getLocale();
        ResourceBundle messages = ResourceBundle.getBundle("messages", userLocale);
        return StringEscapeUtils.unescapeHtml4(messages.getString(i18nLookupKey));
    }
}