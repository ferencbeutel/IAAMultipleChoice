package de.nordakademie.multiplechoice.model;

import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by MHORT on 24.10.2016.
 */
public enum QuestionType {
    Single("single"),
    Multiple("multiple"),
    Gap("cloze");
    private final String realVal;

    QuestionType(String s) {
        this.realVal = s;
    }
    private static Map<String, QuestionType> map = new HashMap<String, QuestionType>();
    static {
        for (QuestionType qEnum : QuestionType.values()) {
            map.put(qEnum.realVal, qEnum);
        }
    }
    public static QuestionType getName(String qType) {
        return map.get(qType);
    }
    @Override
    public String toString() {
        return realVal;
    }

    public static QuestionType getTranslation(String translation){
        HttpServletRequest request = ServletActionContext.getRequest();
        Locale userLocale = request.getLocale();
        System.out.println(userLocale);
        System.out.println("trans: " + translation);
        ResourceBundle messages = ResourceBundle.getBundle("messages", userLocale);
        for (QuestionType qEnum : QuestionType.values()) {
            System.out.println(messages.getString("question."+qEnum.realVal));
            if (messages.getString("question."+qEnum.realVal).equals(translation)){
                System.out.println(messages.getString("question."+qEnum.realVal));
                return qEnum;
            }
        }
        return null;
    }
}