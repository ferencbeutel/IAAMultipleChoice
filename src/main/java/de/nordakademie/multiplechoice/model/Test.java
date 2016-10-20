package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Melanie on 19.10.2016.
 */
@Getter
@Setter
@Entity
public class Test {

    @Basic
    private int credits;
    @Basic
    private String assessmentType;
    @Basic
    private int passingThreshold;
    @Basic
    private int duration;
    @Basic
    private Date beginDate;
    @Basic
    private Date endDate;
    @ManyToOne
    private Seminar seminar;
    @ManyToOne
    private TestCompletion testCompletion;


}
