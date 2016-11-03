package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Melanie on 19.10.2016.
 */
@Setter
@Getter
@Entity
public class Test {

    @Id
    @GeneratedValue
    private long testId;
    private LocalDate beginDate;
    private LocalDate endDate;
    private int minScore;
    @Enumerated(EnumType.STRING)
    private CreditPointsType creditPoints;
    @Enumerated(EnumType.STRING)
    private EvaluationType evaluationType;
    private LocalTime duration;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @OrderBy("position asc")
    private List<Question> questions;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<TestResult> results;

    public int getMaxScore() {
        return questions.stream().mapToInt(Question::getPoints).sum();
    }

    public int calculateTestResult(List<Answer> studentAnswers) {
        int result = 0;
        for (Question question : questions) {
            double partialResult = 0;
            int correctAnswersAmount = question.getAnswers().stream()
                    .filter(Answer::isCorrect)
                    .collect(Collectors.toList()
                    ).size();
            double pointStep = (double) question.getPoints() / correctAnswersAmount;

            if (question.getType() == QuestionType.Gap) {
                for (Answer gapAnswer : question.getAnswers()) {
                    Answer studentGapAnswer = studentAnswers.stream()
                            .filter(answer -> answer.equals(gapAnswer))
                            .collect(Collectors.toList()).get(0);
                    if (studentGapAnswer.getText().toLowerCase().equals(gapAnswer.getText().toLowerCase())) {
                        partialResult = partialResult + pointStep;
                    } else {
                        if (evaluationType == EvaluationType.FATAL) {
                            partialResult = 0;
                            break;
                        } else if (evaluationType == EvaluationType.SUBSTRACT) {
                            partialResult = partialResult - pointStep;
                        }
                    }
                }
            } else {
                for (Answer answer : studentAnswers) {

                    if (question.getAnswers().contains(answer)) {
                        if (question.getAnswers().get(question.getAnswers().indexOf(answer)).isCorrect()) {
                            partialResult = partialResult + pointStep;
                        } else {
                            if (evaluationType == EvaluationType.FATAL) {
                                partialResult = 0;
                                break;
                            } else if (evaluationType == EvaluationType.SUBSTRACT) {
                                partialResult = partialResult - pointStep;
                            }
                        }
                    }
                }
            }

            if (partialResult < 0) {
                partialResult = 0;
            }

            result = result + (int) Math.round(partialResult);
        }
        return result;
    }
}
