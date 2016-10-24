package de.nordakademie.multiplechoice.model;

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

    @Override
    public String toString() {
        return realVal;
    }
}