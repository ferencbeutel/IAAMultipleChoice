package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Created by Melanie on 19.10.2016.
 */
@Getter
@Setter
@Entity
public class Lecturer {

    //TODO: Implement ID (Matrikelnummer aus User)
    @Basic
    private String userMail;
}
