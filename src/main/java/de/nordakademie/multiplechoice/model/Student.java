package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Melanie on 19.10.2016.
 */
@Getter
@Setter
@Entity
public class Student {

    @Id
    @OneToOne
    private User user;
    @ManyToOne
    private Seminar seminar;
    @ManyToOne
    private TestCompletion testCompletion;

}
