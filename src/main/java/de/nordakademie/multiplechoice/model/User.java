package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

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
    private String surName;
    @Id
    @Basic
    private String email;
    @Basic
    private String password;
    @Basic
    private String regToken;
}
