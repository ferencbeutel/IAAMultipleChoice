package de.nordakademie.multiplechoice.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * Created by Melanie on 19.10.2016.
 */

@Getter
@Setter
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Basic
    private String text;
    @NaturalId
    private int position;
    //@Value
    private boolean correctness;


}
