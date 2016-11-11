package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

/**
 * This class represents a student
 * A student is a type of user who can enroll in seminars and carry out tests
 *
 * @author Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
@Setter
@Getter
@Entity
public class Student extends User {
  /**
   * List of seminars student is attending or has attended
   */
  @ManyToMany(mappedBy = "participants", fetch = FetchType.EAGER)
  @OrderBy("beginDate desc")
  private Set<Seminar> seminars;
  /**
   * results for tests of seminars that student carried out
   */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @Fetch(value = FetchMode.SUBSELECT)
  private Set<TestResult> results;

  /**
   * Creation of a HashCode of the student
   *
   * @return hashcode of student
   */
  @Override
  public int hashCode() {
    return (int) (getUserId() ^ (getUserId() >>> 32));
  }

  /**
   * This method is used to compare student objects
   *
   * @param o object as comparison
   *
   * @return truth value if both student object are equal
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Student student = (Student) o;

    return getUserId() == student.getUserId();
  }
}
