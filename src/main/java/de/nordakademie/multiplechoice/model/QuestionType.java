package de.nordakademie.multiplechoice.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MHORT on 24.10.2016.
 */
public enum QuestionType {
    Single("Single Choice"),
    Multiple("Multiple Choice"),
    Gap("Cloze Text");

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
}