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

    @Basic
    private Date endDate;
    @Basic
    private Date startDate;
    @Basic
    private int numberOfParticipants;
    @Basic
    private String description;
    @Basic
    private String name;
    @ManyToOne
    private Lecturer lecturer;



}
