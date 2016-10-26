package de.nordakademie.multiplechoice.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MHORT on 24.10.2016.
 */
public enum EvaluationType {
    SUBSTRACT("Loose a point on wrong answer"),
    IGNORE("Get 0 Points for wrong answer");

    private final String realVal;
    
    EvaluationType(String s) {
        this.realVal = s;
    }

    private static Map<String, EvaluationType> map = new HashMap<String, EvaluationType>();
    static {
        for (EvaluationType evalEnum : EvaluationType.values()) {
            map.put(evalEnum.realVal, evalEnum);
        }
    }
    public static EvaluationType getName(String evalType) {
        return map.get(evalType);
    }
    
    @Override
    public String toString() {
        return realVal;
    }
}