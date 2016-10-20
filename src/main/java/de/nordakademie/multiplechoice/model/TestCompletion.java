package de.nordakademie.multiplechoice.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Basic;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by Melanie on 20.10.2016.
 */
public class TestCompletion {

    @Basic
    private int score;
    @Basic
    private Date startDate;
    @NaturalId
    private Student student;
    @NaturalId
    private Test test;
    }
