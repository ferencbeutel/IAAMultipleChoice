package de.nordakademie.multiplechoice.model;

import lombok.Getter;



/**
 * Created by MHORT on 24.10.2016.
 */
public enum CreditPointsType {
    HALF("0.5"),
    THREEQUARTER("0.75"),
    ONE("1");

    @Getter
    private final String realVal;

    CreditPointsType(final String s) {
        this.realVal = s;
    }
}