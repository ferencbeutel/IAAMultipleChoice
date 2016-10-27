package de.nordakademie.multiplechoice.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MHORT on 24.10.2016.
 */
public enum CreditPointsType {
    HALF("0.5"),
    THREEQUARTER("0.75"),
    ONE("1");

    @Getter
    private final String realVal;

    CreditPointsType(String s) {
        this.realVal = s;
    }
}