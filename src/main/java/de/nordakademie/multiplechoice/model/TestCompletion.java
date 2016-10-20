package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Melanie on 20.10.2016.
 */
@Getter
@Setter
@Entity
public class TestCompletion {

    @Basic
    private int score;
    @Basic
    private Date startDate;
    @Id
    private Student student;
    @Id
    private Test test;
    }
