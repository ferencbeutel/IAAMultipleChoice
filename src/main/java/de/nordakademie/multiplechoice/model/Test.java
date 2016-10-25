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
    private CreditPointsType creditPoints;
    private EvaluationType evaluationType;
    private LocalTime duration;
    @OneToMany(cascade= CascadeType.ALL)
    private Set<Question> questions;
    @OneToMany(cascade=CascadeType.ALL)
    private Set<TestResult> results;

    enum CreditPointsType {
        HALF("0.5"),
        THREEQUARTER("0.75"),
        ONE("1");

        private final String realVal;

        CreditPointsType(String s) {
            this.realVal = s;
        }

        @Override
        public String toString() {
            return realVal;
        }
    }

    enum EvaluationType {
        SUBSTRACT("Loose a point on wrong answer"),
        IGNORE("Get 0 Points for wrong answer");

        private final String realVal;

        EvaluationType(String s) {
            this.realVal = s;
        }

        @Override
        public String toString() {
            return realVal;
        }
    }
}
