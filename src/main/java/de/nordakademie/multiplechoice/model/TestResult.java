package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


/**
 * This class represents a result to a test
 *
 * @author Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
@Setter
@Getter
@Entity
public class TestResult {

  @Id
  @GeneratedValue
  private long testResultId;
  /**
   * Amount of points achieved
   */
  private Integer points;
  /**
   * Point of time student started the test
   */
  private LocalDateTime startDateTime;
  /**
   * Token for student to access the test
   */
  private String accessToken;

  /**
   * Creation of a HashCode of the testresult
   *
   * @return hashcode of testresult
   */
  @Override
  public int hashCode() {
    return (int) (testResultId ^ (testResultId >>> 32));
  }

  /**
   * This method is used to compare to testresult object
   *
   * @param o object that is being used as comparison
   *
   * @return Boolean whether both objects are identical
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    TestResult that = (TestResult) o;

    return testResultId == that.testResultId;
  }
}
