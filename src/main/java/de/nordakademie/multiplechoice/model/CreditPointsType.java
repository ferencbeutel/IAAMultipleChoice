package de.nordakademie.multiplechoice.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MHORT on 24.10.2016.
 */
public enum CreditPointsType {
    HALF("0.5"),
    THREEQUARTER("0.75"),
    ONE("1");

    private final String realVal;

    private static Map<String, CreditPointsType> map = new HashMap<String, CreditPointsType>();

    static {
        for (CreditPointsType pointEnum : CreditPointsType.values()) {
            map.put(pointEnum.realVal, pointEnum);
        }
    }

    public static CreditPointsType getName(String cp) {
        return map.get(cp);
    }
    CreditPointsType(String s) {
        this.realVal = s;
    }

    @Override
    public String toString() {
        return realVal;
    }
}