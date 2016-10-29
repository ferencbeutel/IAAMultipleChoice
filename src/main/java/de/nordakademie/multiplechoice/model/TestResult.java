package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by Melanie on 19.10.2016.
 */
@Setter
@Getter
@Entity
public class TestResult {

    @Id
    @GeneratedValue
    private long testResultId;
    private int points;
    private LocalDateTime startDateTime;
    private String accessToken;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestResult that = (TestResult) o;

        return testResultId == that.testResultId;

    }

    @Override
    public int hashCode() {
        return (int) (testResultId ^ (testResultId >>> 32));
    }
}
