package de.nordakademie.multiplechoice.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * Created by Melanie on 19.10.2016.
 */
@Getter
@Setter
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NaturalId
    private int points;
    @Basic
    private String text;
    @Basic
    private String type;

    //add test-seminar-ps
}
