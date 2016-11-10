package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


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
    @Column(length = 500)
    private String text;
    private int points;
    private int position;
    private QuestionType type;
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("position asc")
    private List<Answer> answers;
}

