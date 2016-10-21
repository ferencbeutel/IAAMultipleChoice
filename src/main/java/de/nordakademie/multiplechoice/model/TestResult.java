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
    private long restResultId;
    private int points;
    private LocalDateTime startDateTime;
}
