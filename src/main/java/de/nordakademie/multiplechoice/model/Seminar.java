package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.sql.Date;
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
    private Date beginDate;
    @NaturalId
    private Date endDate;
    private String description;
    private int maxParticipants;
    @ManyToMany
    @JoinTable
    private Set<Student> participants;
    @ManyToOne
    private Lecturer lecturer;
}
