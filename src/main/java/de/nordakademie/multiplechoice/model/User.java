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

    @Id
    @GeneratedValue
    private long id;
    @NaturalId
    private String email;
    private String name;
    private String surName;
    private String password;
    private String regToken;
    private boolean regComplete;
}
