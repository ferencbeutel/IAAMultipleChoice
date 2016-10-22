package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Melanie on 19.10.2016.
 */
@Setter
@Getter
@Entity
public class Lecturer {

    @Id
    @GeneratedValue
    private long lecturerId;
    @NaturalId
    @OneToOne(cascade=CascadeType.ALL)
    private User user;
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "lecturer")
    private Set<Seminar> seminars;
    @OneToMany(cascade=CascadeType.ALL)
    private Set<Test> tests;
}