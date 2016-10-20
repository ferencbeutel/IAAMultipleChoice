package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

/**
 * Created by Melanie on 19.10.2016.
 */
@Getter
@Setter
@Entity
public class Test implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long testId;
    private int credits;
    private String assessmentType;
    private int passingThreshold;
    private int duration;
    private Date beginDate;
    private Date endDate;
    @OneToOne
    private Seminar seminar;
    @OneToMany(mappedBy = "test")
    private Set<TestCompletion> testCompletion;
}
