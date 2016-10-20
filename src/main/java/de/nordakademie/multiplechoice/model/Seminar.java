package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Melanie on 19.10.2016.
 */
@Getter
@Setter
@Entity
public class Seminar {

    @Column(name = "end_date")
    private String endDate;
    @Column(name = "start_date")
    private String startDate;
    @NaturalId
    private int numberOfParticipants;
    @Basic
    private String description;
    @Basic
    private String name;

    //DozentBenutzerMail fremdschlüssel hinzufügen
}
