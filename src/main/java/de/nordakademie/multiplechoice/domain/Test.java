package de.nordakademie.multiplechoice.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Melanie on 19.10.2016.
 */
@Getter
@Setter
@Entity

public class Test {

@NaturalId
private int credit;
@Basic
    private String assessmentType;
    @NaturalId
    private double pointsToPass;
    @NaturalId
    private int duration;
    @Column(name = "begin_date")
    private String beginDate;
    @Column(name = "end_date")
    private String endDate;

    //Seminar-PS noch hinzufügen.




}
