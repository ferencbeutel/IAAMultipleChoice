package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Melanie on 19.10.2016.
 */
@Getter
@Setter
@Entity
public class Seminar {

    @Id
    @Basic
    private Date endDate;
    @Id
    @Basic
    private Date startDate;
    @Basic
    private int numberOfParticipants;
    @Basic
    private String description;
    @Id
    @Basic
    private String name;
    @ManyToOne
    private Lecturer lecturer;



}
