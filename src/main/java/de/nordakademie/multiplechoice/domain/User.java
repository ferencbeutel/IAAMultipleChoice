package de.nordakademie.multiplechoice.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * Created by Melanie on 19.10.2016.
 */
@Setter
@Getter
@Entity

public class User {

    @Basic
    private String name;
    @Basic
    private String surname;
    @NaturalId
    private String email;
    @Basic
    private String password;
}
