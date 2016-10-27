package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

/**
 * Created by Melanie on 19.10.2016.
 */
@Setter
@Getter
@Entity
public class Test {

    @Id
    @GeneratedValue
    private long testId;
    private LocalDate beginDate;
    private LocalDate endDate;
    private int minScore;
    @Enumerated(EnumType.STRING)
    private CreditPointsType creditPoints;
    @Enumerated(EnumType.STRING)
    private EvaluationType evaluationType;
    private LocalTime duration;
    @OneToMany(cascade= CascadeType.ALL)
    private Set<Question> questions;
    @OneToMany(cascade=CascadeType.ALL)
    private Set<TestResult> results;
}
