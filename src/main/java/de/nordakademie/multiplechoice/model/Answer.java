package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * This class represents an answer
 * Answers are part of a question and have a truth value assigned in the context of the question
 *
 * @author Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
@Setter
@Getter
@Entity
public class Answer {

  @Id
  @GeneratedValue
  private long answerId;
  /**
   * Text of answer
   * will be displayed to students when assessing a question
   */
  private String text;
  /**
   * truth value if answer is part of the correct answers to a question
   */
  private boolean correct;
  /**
   * position of answer in set of answers for question
   * determines the display position
   */
  private int position;

  /**
   * Creation of a HashCode of the answer
   *
   * @return hashcode of anwer
   */
  @Override
  public int hashCode() {
    return (int) (answerId ^ (answerId >>> 32));
  }

  /**
   * This method is used to compare to answer object
   *
   * @param o object that is being used as comparison
   *
   * @return Boolean whether both objects are identical
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Answer answer = (Answer) o;

    return answerId == answer.answerId;
  }
}
