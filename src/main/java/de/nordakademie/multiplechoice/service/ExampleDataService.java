package de.nordakademie.multiplechoice.service;

import de.nordakademie.multiplechoice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by ferencbeutel on 05.11.16.
 */
@Service
public class ExampleDataService {

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TestResultService testResultService;

    private static final String STUDENT_FIRST_NAMES_FILE = "example-data/firstname-student-example-data.txt";
    private static final String LECTURER_FIRST_NAMES_FILE = "example-data/firstname-lecturer-example-data.txt";
    private static final String LAST_NAMES_FILE = "example-data/lastname-example-data.txt";
    private static final String SEMINAR_NAMES_FILE = "example-data/seminarname-example-data.txt";
    private static final String SEMINAR_DESCRIPTIONS_FILE = "example-data/seminardescription-example-data.txt";

    private final List<String> studentFirstNames;
    private final List<String> lecturerFirstNames;
    private final List<String> surNames;
    private final List<String> seminarNames;
    private final List<String> seminarDescriptions;

    private List<String> usedStudentNames = new ArrayList<>();
    private List<String> usedLecturerNames = new ArrayList<>();

    public ExampleDataService() throws URISyntaxException, IOException {
        studentFirstNames = Files.readAllLines(Paths.get(getClass().getClassLoader().getResource(STUDENT_FIRST_NAMES_FILE).toURI()));
        lecturerFirstNames = Files.readAllLines(Paths.get(getClass().getClassLoader().getResource(LECTURER_FIRST_NAMES_FILE).toURI()));
        surNames = Files.readAllLines(Paths.get(getClass().getClassLoader().getResource(LAST_NAMES_FILE).toURI()));
        seminarNames = Files.readAllLines(Paths.get(getClass().getClassLoader().getResource(SEMINAR_NAMES_FILE).toURI()));
        seminarDescriptions = Files.readAllLines(Paths.get(getClass().getClassLoader().getResource(SEMINAR_DESCRIPTIONS_FILE).toURI()));
    }

    public void generateTestData(final int quantStudents, final int quantSeminars, final int quantLecturer) {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < quantStudents; i++) {
            Student student = randomStudent();
            if (student == null) {
                i--;
                continue;
            }
            student = studentService.createOrUpdate(student);
            students.add(student);
        }

        int remainingSeminars = quantSeminars;
        int remainingLecturers = quantLecturer;
        for (int i = 0; i < quantLecturer; i++) {
            Lecturer lecturer = randomLecturer();
            if (lecturer == null) {
                i--;
                continue;
            }
            int seminarsPerLecturer;
            // if last lecturer, give him all left seminars
            // else, give him #remainingSeminars / #remainingLecturers, in order to distribute seminars evenly
            if (i + 1 == quantLecturer) {
                seminarsPerLecturer = remainingSeminars;
            } else {
                seminarsPerLecturer = (int) Math.ceil((long) remainingSeminars / remainingLecturers);
                remainingSeminars = remainingSeminars - seminarsPerLecturer;
                remainingLecturers--;
            }
            for (int j = 0; j < seminarsPerLecturer; j++) {
                Seminar seminar = randomSeminar();
                seminar.setLecturer(lecturer);
                // give ~80% of Seminars a test
                if (Math.random() < 0.8) {
                    Test test = randomTest(seminar.getBeginDate());
                    // every test should have between 5 and 15 questions
                    int amountOfQuestions = randomInt(5, 15);
                    List<Question> questions = new ArrayList<>();
                    for (int k = 0; k < amountOfQuestions; k++) {
                        Question question = randomQuestion(k);
                        // every question should have between 2 and 8 answers (1-7 generated and one always true)
                        int amountOfAnswers = randomInt(1, 7);
                        List<Answer> answers = new ArrayList<>();
                        if (question.getType() == QuestionType.Single) {
                            for (int l = 0; l < amountOfAnswers; l++) {
                                answers.add(randomAnswer(l, QuestionGenerationType.FALSE));
                            }
                        } else if(question.getType() == QuestionType.Gap){
                            for (int l = 0; l < amountOfAnswers; l++) {
                                answers.add(randomAnswer(l, QuestionGenerationType.TRUE));
                            }
                        }
                        else {
                            for (int l = 0; l < amountOfAnswers; l++) {
                                answers.add(randomAnswer(l, QuestionGenerationType.GENERATE));
                            }
                        }
                        answers.add(randomAnswer(answers.size(), QuestionGenerationType.TRUE));
                        question.setAnswers(answers);
                        question.setPoints(answers.size());

                        if (question.getType() == QuestionType.Gap) {
                            question.setText(question.getText() + " with gaps: ");
                            for (int l = 0; l < question.getAnswers().size(); l++) {
                                question.setText(question.getText() + " [...](" + question.getAnswers().get(l).getText() + "),");
                            }
                        }
                        questions.add(question);
                    }
                    test.setQuestions(questions);
                    seminar.setTest(test);
                }
                // ~15% of all Students are participants per seminar, but not more than max participants
                Set<Student> participants = students.stream()
                        .filter(student -> Math.random() < 0.15)
                        .limit(seminar.getMaxParticipants())
                        .collect(Collectors.toSet());
                // if there is a test and the test beginDate is not in the future, add some test results
                if (seminar.getTest() != null) {
                    LocalDate testBeginDate = seminar.getTest().getBeginDate();
                    LocalDate now = LocalDate.now();
                    if (testBeginDate.isBefore(now) || testBeginDate.isEqual(now)) {
                        for (Student student : participants) {
                            // ~ 50% of participants have done the test already
                            if (Math.random() < 0.5) {
                                LocalDate testEndDate = seminar.getTest().getEndDate();
                                int daysBetween = (int) ChronoUnit.DAYS.between(testBeginDate, testEndDate);
                                TestResult result = randomTestResult(testBeginDate.plusDays(randomInt(0, daysBetween)).atStartOfDay(), seminar.getTest().getMaxScore());
                                result = testResultService.createOrUpdate(result);
                                student.getResults().add(result);
                                students.set(students.indexOf(student), studentService.createOrUpdate(student));
                                seminar.getTest().getResults().add(result);
                            }
                        }
                    }
                    lecturer.getTests().add(seminar.getTest());
                }

                seminar.setParticipants(participants);
                lecturer.getSeminars().add(seminar);
            }
            lecturerService.createOrUpdate(lecturer);
        }

    }

    private Answer randomAnswer(final int position, QuestionGenerationType genType) {
        Answer answer = new Answer();
        answer.setPosition(position);

        switch (genType) {
            case GENERATE:
                if (Math.random() < 0.5) {
                    answer.setCorrect(true);
                    answer.setText("Correct Answer");
                } else {
                    answer.setCorrect(false);
                    answer.setText("False Answer");
                }
                break;
            case TRUE:
                answer.setCorrect(true);
                answer.setText("Correct Answer");
                break;
            case FALSE:
                answer.setCorrect(false);
                answer.setText("False Answer");
                break;
        }
        return answer;
    }

    private Lecturer randomLecturer() {
        String name = randomString(lecturerFirstNames);
        String surName = randomString(surNames);
        String completeName = name + "-" + surName;
        if (usedLecturerNames.contains(completeName)) {
            return null;
        }
        usedLecturerNames.add(completeName);
        Lecturer lecturer = new Lecturer();
        lecturer.setRegComplete(true);
        lecturer.setName(name);
        lecturer.setSurName(surName);
        lecturer.setEmail(completeName + "@nordakademie.de");
        lecturer.setPassword("genExamplePW");
        lecturer.setTests(new HashSet<>());
        lecturer.setSeminars(new HashSet<>());

        return lecturer;
    }

    private Question randomQuestion(final int position) {
        Question question = new Question();
        question.setPosition(position);
        QuestionType type = randomEnum(QuestionType.class);
        question.setType(type);
        String text = "Example Question of type: " + type.getRealVal();

        question.setText(text);

        return question;
    }

    private Seminar randomSeminar() {
        Seminar seminar = new Seminar();
        seminar.setName(randomString(seminarNames));
        seminar.setDescription(randomString(seminarDescriptions));
        seminar.setMaxParticipants(randomInt(0, 40));

        LocalDate beginDate = randomDate();
        seminar.setBeginDate(beginDate);
        seminar.setEndDate(beginDate.plusDays(randomInt(0, 4)));
        seminar.setParticipants(new HashSet<>());

        return seminar;
    }

    private Student randomStudent() {
        String name = randomString(studentFirstNames);
        String surName = randomString(surNames);
        String completeName = name + "-" + surName;
        if (usedStudentNames.contains(completeName)) {
            return null;
        }
        usedStudentNames.add(completeName);
        Student student = new Student();
        student.setRegComplete(true);
        student.setName(name);
        student.setSurName(surName);
        student.setPassword("genExamplePW");
        student.setEmail(completeName + "@nordakademie.de");
        student.setSeminars(new HashSet<>());
        student.setResults(new HashSet<>());

        return student;
    }

    private Test randomTest(final LocalDate beginDateBase) {
        Test test = new Test();
        LocalDate beginDate = beginDateBase.plusDays(randomInt(0, 10));
        test.setBeginDate(beginDate);
        test.setEndDate(beginDate.plusDays(randomInt(2, 10)));
        test.setCreditPoints(randomEnum(CreditPointsType.class));
        test.setEvaluationType(randomEnum(EvaluationType.class));
        test.setDuration(randomTime());
        test.setPassingThreshold(50);
        test.setQuestions(new ArrayList<>());
        test.setResults(new HashSet<>());

        return test;
    }

    private TestResult randomTestResult(final LocalDateTime startTime, final int maxScore) {
        TestResult result = new TestResult();
        result.setStartDateTime(startTime);
        result.setPoints(randomInt(0, maxScore));

        return result;
    }

    private Integer randomInt(final int lowerBoundary, final int upperBoundary) {
        return lowerBoundary + ((int) Math.round(Math.random() * upperBoundary) - lowerBoundary);
    }

    private LocalDate randomDate() {
        int offset = randomInt(0, 365);
        if (Math.random() < 0.5) {
            return LocalDate.now().minusDays(offset);
        } else {
            return LocalDate.now().plusDays(offset);
        }
    }

    private LocalTime randomTime() {
        int offset = randomInt(30, 90);
        LocalTime time = LocalTime.MIN;
        return time.plusMinutes(offset);
    }

    private String randomString(final List<String> possibleStrings) {
        return possibleStrings.get(randomInt(0, possibleStrings.size() - 1));
    }

    private <T extends Enum<?>> T randomEnum(final Class<T> clazz) {
        int x = randomInt(0, clazz.getEnumConstants().length - 1);
        return clazz.getEnumConstants()[x];
    }

    private enum QuestionGenerationType {
        GENERATE,
        TRUE,
        FALSE
    }
}