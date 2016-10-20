package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Melanie on 19.10.2016.
 */
@Getter
@Setter
@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long studentId;
    @OneToOne
    private User user;
    @ManyToMany
    private Set<Seminar> seminar;
    @OneToMany(mappedBy = "student")
    private Set<TestCompletion> testCompletion;

}
