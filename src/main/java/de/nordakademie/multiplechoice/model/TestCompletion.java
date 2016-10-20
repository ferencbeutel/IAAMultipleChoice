package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Melanie on 20.10.2016.
 */
@Getter
@Setter
@Entity
public class TestCompletion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int score;
    private Date startDate;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Test test;
    }
