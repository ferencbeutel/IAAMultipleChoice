package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Created by Melanie on 19.10.2016.
 */
@Getter
@Setter
@Entity
public class Student {

    @OneToOne
    @Basic
    private String userMail;
    @OneToOne
    private User user;
    @ManyToOne
    private Seminar seminar;
    @ManyToOne
    private TestCompletion testCompletion;

}
