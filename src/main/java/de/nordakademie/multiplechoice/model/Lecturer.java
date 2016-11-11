package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * This class represents a lecturer
 * Lecturers are users, who amongst others can create Seminars and test
 *
 * @author Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
@Setter
@Getter
@Entity
public class Lecturer extends User {

  /**
   * Set of seminars that the lecturer holds, or held
   */
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "lecturer", fetch = FetchType.EAGER)
  @OrderBy("beginDate desc")
  private Set<Seminar> seminars;
  /**
   * Set of test that the lecturer created for the seminars
   */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<Test> tests;
}