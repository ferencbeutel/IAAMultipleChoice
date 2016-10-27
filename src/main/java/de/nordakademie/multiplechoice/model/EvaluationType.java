package de.nordakademie.multiplechoice.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MHORT on 24.10.2016.
 */
public enum EvaluationType {
    SUBSTRACT("Loose a point on wrong answer"),
    IGNORE("Get 0 Points for wrong answer");

    @Getter
    private final String realVal;
    
    EvaluationType(String s) {
        this.realVal = s;
    }
}