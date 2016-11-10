package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * Created by Melanie on 19.10.2016.
 */
@Setter
@Getter
@Entity
public class Seminar {

    @Id
    @GeneratedValue
    private long seminarId;
    private String name;
    private LocalDate beginDate;
    private LocalDate endDate;
    @Column(length = 5000)
    private String description;
    private int maxParticipants;
    @JoinTable
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Student> participants;
    @ManyToOne
    private Lecturer lecturer;
    @OneToOne(cascade = CascadeType.ALL)
    private Test test;

    public String getHtmlDescription() {
        String escaped = StringEscapeUtils.escapeHtml4(description);
        return escaped.replace("\r\n", "<br/>");
    }
}
