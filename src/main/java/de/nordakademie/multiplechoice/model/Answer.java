package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


/**
 * Created by Melanie on 19.10.2016.
 */
@Setter
@Getter
@Entity
public class Answer {

    @Id
    @GeneratedValue
    private long answerId;
    private String text;
    private boolean correct;
    private int position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        return answerId == answer.answerId;

    }

    @Override
    public int hashCode() {
        return (int) (answerId ^ (answerId >>> 32));
    }
}
