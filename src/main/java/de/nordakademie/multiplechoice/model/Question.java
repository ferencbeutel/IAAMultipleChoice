package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Melanie on 19.10.2016.
 */
@Setter
@Getter
@Entity
public class Question {

    @Id
    @GeneratedValue
    private long questionId;
    private String text;
    private int points;
    private int position;
    private QuestionType type;
    @OneToMany(cascade=CascadeType.ALL)
    private Set<Answer> answers;


}

