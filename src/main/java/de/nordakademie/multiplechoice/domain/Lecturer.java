package de.nordakademie.multiplechoice.domain;

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

    @NaturalId
    private String userMail;
}
