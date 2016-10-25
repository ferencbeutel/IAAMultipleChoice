package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * Created by Melanie on 19.10.2016.
 */
@Setter
@Getter
@Entity
public class Seminar {

    @Id
    @GeneratedValue
    private long seminarId;
    @NaturalId
    private String name;
    @NaturalId
    private LocalDate beginDate;
    @NaturalId
    private LocalDate endDate;
    private String description;
    private int maxParticipants;
    @JoinTable
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Student> participants;
    @ManyToOne
    private Lecturer lecturer;
}
