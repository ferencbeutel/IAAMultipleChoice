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
public class Student {

    @Id
    @GeneratedValue
    private long studentId;
    @NaturalId
    @OneToOne(cascade=CascadeType.ALL)
    private User user;
    @ManyToMany(mappedBy = "participants", fetch = FetchType.EAGER)
    private Set<Seminar> seminars;
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TestResult> results;
}
