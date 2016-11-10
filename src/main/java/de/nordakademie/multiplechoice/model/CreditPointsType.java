package de.nordakademie.multiplechoice.model;

import lombok.Getter;



/**
 * This class represents the Creditpoints that a test can have
 * Each test gets a amount of credits assigned
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
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