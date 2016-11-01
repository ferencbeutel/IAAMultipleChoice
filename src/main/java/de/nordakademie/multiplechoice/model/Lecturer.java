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
public class Lecturer extends User {

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "lecturer", fetch = FetchType.EAGER)
    @OrderBy("beginDate desc")
    private Set<Seminar> seminars;
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Test> tests;
}