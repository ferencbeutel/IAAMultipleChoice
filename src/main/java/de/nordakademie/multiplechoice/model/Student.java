package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Melanie on 19.10.2016.
 */
@Setter
@Getter
@Entity
public class Student extends User {

    @ManyToMany(mappedBy = "participants", fetch = FetchType.EAGER)
    private Set<Seminar> seminars;
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TestResult> results;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return getUserId() == student.getUserId();

    }

    @Override
    public int hashCode() {
        return (int) (getUserId() ^ (getUserId() >>> 32));
    }
}
