package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * This class represents a seminar
 * A seminar is created by a lecturer and can be attended by students
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
@Setter
@Getter
@Entity
public class Seminar {

    @Id
    @GeneratedValue
    private long seminarId;
    /**
     * Name of the seminar
     * Used to display seminar in lists for student and lecturer
     */
    private String name;
    /**
     * Begin of seminar
     */
    private LocalDate beginDate;
    /**
     * End of seminar
     */
    private LocalDate endDate;
    /**
     * Text to describe the content of seminar
     */
    @Column(length = 5000)
    private String description;
    /**
     * maximum number of participants for seminar
     * can't be exceeded
     */
    private int maxParticipants;
    /**
     * List of students that enrolled in the seminar
     */
    @JoinTable
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Student> participants;
    /**
     * Lecturer who created the seminar
     */
    @ManyToOne
    private Lecturer lecturer;
    /**
     * test that has been created for seminar
     * has to be done by students
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Test test;

    /**
     * This methods returns the html escaped text of seminar description
     * @return escaped string
     */
    public String getHtmlDescription() {
        String escaped = StringEscapeUtils.escapeHtml4(description);
        return escaped.replace("\r\n", "<br/>");
    }
}
