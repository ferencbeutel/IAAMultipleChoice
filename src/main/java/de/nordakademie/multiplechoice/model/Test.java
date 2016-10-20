package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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

    //TODO: Seminar-PS noch hinzufügen.
}
