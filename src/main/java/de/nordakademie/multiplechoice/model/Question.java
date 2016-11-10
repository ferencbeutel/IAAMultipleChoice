package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


/**
 * This class represents a question
 * Questions are part of tests and used to determine if students passed the test
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
@Setter
@Getter
@Entity
public class Question {

    @Id
    @GeneratedValue
    private long questionId;
    /**
     * Text of question that is displayed to students
     */
    @Column(length = 500)
    private String text;
    /**
     * Points that can be obtained by answering the question correctly
     * Students might earn parts of the total points by answering the question partially correct
     */
    private int points;
    /**
     * position of question in test
     */
    private int position;
    /**
     * Type of question
     * Either single choice, multiple choice or gap text
     */
    private QuestionType type;
    /**
     * Answers to question
     * Students are asked to pick the correct answers for the question
     * The selection of answers determine the amount of points earned for the question
     */
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("position asc")
    private List<Answer> answers;
}

