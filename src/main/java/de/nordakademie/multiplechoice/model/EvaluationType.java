package de.nordakademie.multiplechoice.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MHORT on 24.10.2016.
 */
public enum EvaluationType {
    SUBSTRACT("Loose points on wrong answer"),
    FATAL("Get total 0 points for single wrong answer");

    @Getter
    private final String realVal;
    
    EvaluationType(final String s) {
        this.realVal = s;
    }
}