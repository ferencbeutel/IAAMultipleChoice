package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * This class represents a user
 * Users can differ in type
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "userType")
public class User {

    @Id
    @GeneratedValue
    private long userId;
    /**
     * Mail used for registration
     */
    @NaturalId
    private String email;
    private String name;
    private String surName;
    private String password;
    /**
     * Token send by mail to finish registration
     */
    private String regToken;
    /**
     * Used to show is registration is complete
     * User can log in when registration is complete
     */
    private boolean regComplete;
}

