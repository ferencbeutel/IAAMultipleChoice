package de.nordakademie.multiplechoice.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represents a test
 * Tests are assigned to seminars, are created by lecturers and can be carried out by students
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
@Setter
@Getter
@Entity
public class Test {

    @Id
    @GeneratedValue
    private long testId;
    /**
     * Begin date of test
     * Tests can't be carried out before the begin date
     */
    private LocalDate beginDate;
    /**
     * End date of test
     * Tests can't be carried out afterwards
     */
    private LocalDate endDate;
    /**
     * Percentage of total points student have to get in order to pass the test
     */
    private int passingThreshold;
    /**
     * Amount of credit points student get for passing the test
     */
    @Enumerated(EnumType.STRING)
    private CreditPointsType creditPoints;
    /**
     * Evaluation type of test
     * Determining have wrong answers will be evaluated
     */
    @Enumerated(EnumType.STRING)
    private EvaluationType evaluationType;
    /**
     * Maximum duration for carrying out the test
     */
    private LocalTime duration;
    /**
     * Questions that are part of the test
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @OrderBy("position asc")
    private List<Question> questions;
    /**
     * List of testresults students have carried out
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<TestResult> results;

    /**
     * This method will compute the maximum achieveable score over all questions
     * @return maximum score of test
     */
    public int getMaxScore() {
        return questions.stream().mapToInt(Question::getPoints).sum();
    }

    /**
     *
     * @param studentAnswers answers student has given to questions
     * @return points the student achieved by answering to questions
     */
    public int calculateTestResult(final List<Answer> studentAnswers) {
        int result = 0;
        for (Question question : questions) {
            double partialResult = 0;
            int correctAnswersAmount = question.getAnswers().stream()
                    .filter(Answer::isCorrect)
                    .collect(Collectors.toList()
                    ).size();
            double pointStep = (double) question.getPoints() / correctAnswersAmount;
            // Consider question type
            if (question.getType() == QuestionType.Gap) {
                for (Answer gapAnswer : question.getAnswers()) {
                    Answer studentGapAnswer = studentAnswers.stream()
                            .filter(answer -> answer.equals(gapAnswer))
                            .collect(Collectors.toList()).get(0);
                    // Correct Answer
                    if (studentGapAnswer.getText().toLowerCase().equals(gapAnswer.getText().toLowerCase())) {
                        partialResult = partialResult + pointStep;
                    } else {
                        // Consider Evaluation Type for wrong answer
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
                        //Correct answer
                        if (question.getAnswers().get(question.getAnswers().indexOf(answer)).isCorrect()) {
                            partialResult = partialResult + pointStep;
                        } else {
                            // Consider Evaluation Type for wrong answer
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
