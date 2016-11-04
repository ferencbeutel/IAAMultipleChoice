package de.nordakademie.multiplechoice.action;


import de.nordakademie.multiplechoice.model.*;

import de.nordakademie.multiplechoice.service.LecturerService;

import de.nordakademie.multiplechoice.service.SeminarService;
import de.nordakademie.multiplechoice.service.StudentService;


import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Hendrik on 01.11.2016.
 */
public class InitializeApplicationAction extends BaseAction {


    @Autowired
    StudentService studentService;

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private SeminarService seminarService;

    @Setter
    private Integer quantStudents ;

    @Setter
    private Integer quantLecturer ;

    @Setter
    private Integer quantSeminar ;

    @Setter
    private List<String> singleChoiceAnswers;

    @Setter
    private List<Integer> singleChoiceAnswerValues;

    @Setter
    private List<String> multipleChoiceAnswers;

    @Setter
    private List<Integer> multipleChoiceAnswerValues;

    @Setter
    private List<String> gapAnswers;

    private DateTimeFormatter durationFormatter = DateTimeFormatter.ofPattern("HH:mm");

    private Set<Student> participants = new HashSet<>();

    //List Initialization
    ArrayList<String> studentFirstNameList = new ArrayList<String>();
    ArrayList<String> lastNameList = new ArrayList<String>();
    ArrayList<String> lecturerFirstNameList = new ArrayList<String>();
    ArrayList<String> userEmailList = new ArrayList<String>();
    ArrayList<String> seminarNameList = new ArrayList<String>();
    ArrayList<String> seminarDescriptionList = new ArrayList<String>();
    ArrayList<String> lecturerList = new ArrayList<String>();
    ArrayList<String> studentList = new ArrayList<String>();
    ArrayList<Seminar> seminarList = new ArrayList<Seminar>();

    int arrayposition;
    int maxParticipants;
    String firstName;
    String lastName;
    String email;
    String seminarName;
    String seminarDescription;
    LocalDate seminarBeginDate;
    LocalDate seminarEndDate;
    LocalDate testBeginDate;
    LocalDate testEndDate;
    LocalTime testDuration;


    public String initialize(){
        fillNameList();
        createStudent(quantStudents);
        createLecturer(quantLecturer);
        createSeminar(quantSeminar); //TODO Implement how many seminar should be created
        //TODO Implement Mapping of Students to seminars
        //TODO Implement Test creation and mapping to seminars
        return SUCCESS;
    };

    public ArrayList writeFromFileToList(Path path, ArrayList arrayList) {
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(s -> arrayList.add(s));
        } catch (IOException ex) {
        }
        return arrayList;
    }

    public Integer nextRandomNumber(int possibilities) { //returns a random number between 0 and 'possibilities'-1
        int randNumber;
        randNumber = (int)(Math.random() * possibilities);
        return randNumber;
    }

    public boolean testOrNoTest(){ // In 80% this method will return true
        int number = nextRandomNumber(10);
        if (number <= 6){ //
            return true;
        }
        return false;
    }

    public boolean answerIsTrue(){ // In 70% this method will return true
        int number = nextRandomNumber(10);
        if (number <= 6){
            return false;
        }
        return true;
    }

    public void fillNameList(){ //Todo Fix the path
        Path path = Paths.get("/Users/HendrikPeters/Documents/IAA/hausarbeit/IAAMultipleChoice/src/main/resources/ExampleData/ExampleDataLastNames.txt");
        writeFromFileToList(path,lastNameList);
        path = Paths.get("/Users/HendrikPeters/Documents/IAA/hausarbeit/IAAMultipleChoice/src/main/resources/ExampleData/ExampleDataLecturerFirstName.txt");
        writeFromFileToList(path,lecturerFirstNameList);
        path = Paths.get("/Users/HendrikPeters/Documents/IAA/hausarbeit/IAAMultipleChoice/src/main/resources/ExampleData/ExampleDataStudentFirstName.txt");
        writeFromFileToList(path,studentFirstNameList);
        path = Paths.get("/Users/HendrikPeters/Documents/IAA/hausarbeit/IAAMultipleChoice/src/main/resources/ExampleData/ExampleDataSeminarName.txt");
        writeFromFileToList(path,seminarNameList);
        path = Paths.get("/Users/HendrikPeters/Documents/IAA/hausarbeit/IAAMultipleChoice/src/main/resources/ExampleData/ExampleDataSeminarDescription.txt");
        writeFromFileToList(path,seminarDescriptionList);
    }

    public void createStudent(int number) {
        for (int i = 1; i <= number; i++) {
            Student student = new Student();
            arrayposition = (nextRandomNumber(studentFirstNameList.size())); //Choose random index for studentFirstNameList
            firstName = studentFirstNameList.get(arrayposition);
            arrayposition = (nextRandomNumber(lastNameList.size())); //Choose random index for studentLastNameList
            lastName = lastNameList.get(arrayposition);
            email = (firstName + "_" + lastName + "@nordakademie.de");
            if (lecturerList.contains(email) || studentList.contains(email)){ //Check wheter the combination of First and Last Name already exists or not (unique key = email)
                number ++;
                continue;
            }
            student.setName(firstName);
            student.setSurName(lastName);
            student.setRegComplete(true);
            student.setEmail(email);
            student.setPassword("Passwort1");
            studentService.createOrUpdate(student);
            studentList.add(email);
        }
    };

    public void createLecturer(int number) {
        for (int i = 1; i <= number; i++) {
            Lecturer lecturer = new Lecturer();
            arrayposition = (nextRandomNumber(lecturerFirstNameList.size())); //Choose random index for lecturerFirstNameList
            firstName = lecturerFirstNameList.get(arrayposition);
            arrayposition = (nextRandomNumber(lastNameList.size())); //Choose random index for lecturerLastNameList
            lastName = lastNameList.get(arrayposition);
            email = (firstName + "_" + lastName + "@nordakademie.de");
            if (lecturerList.contains(email) || studentList.contains(email)){ //Check wheter the combination of First and Last Name already exists or not (unique key = email)
                number ++;
                continue;
            }
            lecturer.setName(firstName);
            lecturer.setSurName(lastName);
            lecturer.setRegComplete(true);
            lecturer.setEmail(email);
            lecturer.setPassword("Passwort1");
            lecturerService.save(lecturer);
            userEmailList.add(email);
            lecturerList.add(email);
        }
    };

    public void createSeminar(int number) {
        for (int i = 1; i <= number; i++) {
            Seminar seminar = new Seminar();
            arrayposition = (nextRandomNumber(seminarNameList.size()));   //Choose random index for seminarNameList
            seminarName = seminarNameList.get(arrayposition);
            seminarDescription = seminarDescriptionList.get(arrayposition);
            maxParticipants = (10 + nextRandomNumber(20));                // Max Participants = 5 + random number between 0...9
            seminarBeginDate = LocalDate.now().plusDays((nextRandomNumber(365)+1)); //Startdate = Today + random number of days between 1...365
            seminarEndDate = seminarBeginDate.plusDays(nextRandomNumber(31));          //Enddate = Startdate + random number of days between 0...30
            arrayposition = (nextRandomNumber(lecturerList.size()));  //Choose random index from LecturerList
            Lecturer lecturer = lecturerService.findByMail(lecturerList.get(arrayposition)); //Select Lecturer
            seminar.setLecturer(lecturer);
            seminar.setName(seminarName);
            seminar.setDescription(seminarDescription);
            seminar.setBeginDate(seminarBeginDate);
            seminar.setEndDate(seminarEndDate);
            seminar.setMaxParticipants(maxParticipants);
            int enrolledStudents = nextRandomNumber(maxParticipants); //Choose random number of participants
            if (enrolledStudents >= studentList.size()) {
                enrolledStudents = (studentList.size()-1);
            }
            for (int j = 1; j <= enrolledStudents; j++) { //enroll students
                arrayposition = (nextRandomNumber(studentList.size()));  //Choose random index from StudentList
                Student student = studentService.findByMail(studentList.get(arrayposition)); //Select Student
                if (participants.contains(student)){ //Check whether student is already enrolled for the seminar
                    enrolledStudents ++;
                    continue;
                }
                participants.add(student); //add Student to participants set of the seminar
            }
            seminar.setParticipants(participants);
            if (testOrNoTest()){
                Test test = createTest();
                seminar.setTest(test);
            }
            seminarService.createOrUpdate(seminar);
            seminarList.add(seminar);
            participants.clear();
        }
    }


    public Test createTest(){
        Test test = new Test();
        testBeginDate =  seminarBeginDate.plusDays(nextRandomNumber(31)); // Test-Date Equal/After Seminar beginDate
        testEndDate = testBeginDate.plusDays(nextRandomNumber(40)); //TestEndDate Equal/After TestBeginDate
        test.setBeginDate(testBeginDate);
        test.setEndDate(testEndDate);
        testDuration = LocalTime.parse("00:00",durationFormatter);
        testDuration = testDuration.plusMinutes(nextRandomNumber(120)+1); //Set Duration to at least 1 minute
        test.setDuration(testDuration);
        test.setMinScore(nextRandomNumber(80)+1); // Set minScore to at Least 1
        int creditPoint = nextRandomNumber(3); //Chose  credits random
        switch ( creditPoint){
            case 0:
                test.setCreditPoints(CreditPointsType.HALF);
                break;
            case 1:
                test.setCreditPoints(CreditPointsType.THREEQUARTER);
                break;
            case 2:
                test.setCreditPoints(CreditPointsType.ONE);
        }
        int evalType = nextRandomNumber(2); //choose Evaluation Type of test random
        switch (evalType){
            case 0:
                test.setEvaluationType(EvaluationType.FATAL);
                break;
            case 1:
                test.setEvaluationType(EvaluationType.SUBSTRACT);
        }
        int numberOfQuestions = nextRandomNumber(15); //Set number of Questions (Random 0..14)
        test.setQuestions(createQuestionSet(numberOfQuestions)); //Create questions for test
        return test;
    }

    public List<Question> createQuestionSet(int number){
        List<Question> questionSet = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            Question question = new Question();
            question.setPosition(i-1);
            int points = (nextRandomNumber(5)+1); //Set random number of points 1..5
            question.setPoints(points);
            int questionType = nextRandomNumber(3); // set random question type
            List<Answer> answerSet = new ArrayList<>();
            switch (questionType) {
                case 0:
                    question.setType(QuestionType.Single);
                    question.setText("Dies ist eine Single Choice Frage. Es ist also nur eine Antwort richtig");
                    answerSet = createAnswer(points, question.getType()); //Create answer (number of answers = points)
                    question.setAnswers(answerSet);
                    break;
                case 1:
                    question.setType(QuestionType.Multiple);
                    question.setText("Dies ist eine Multiple Choice Frage. Es sind also vielleicht mehrere Antworten richtig");
                    answerSet = createAnswer(points, question.getType());
                    question.setAnswers(answerSet);
                    break;
                case 2:
                    question.setType(QuestionType.Gap);
                    question.setText("Dies ist ein Lückentext. Beweise Mut zur [...]. Fortes [...] adiuvat. Und obwohl es "+points+" Punkte gibt, gibt es hier nur 2 Lücken.");
                    answerSet = createAnswer(2, question.getType()); //Create answer for only two gaps
                    question.setAnswers(answerSet);
                    break;
            }
            questionSet.add(question);
        }
        return questionSet;


    }

    public List<Answer> createAnswer(int numberOfAnswers, QuestionType type){
        boolean allAnswersFalse = true;
        List<Answer> answerSet = new ArrayList<>();
        for (int i = 1; i <= numberOfAnswers; i++){
            Answer answer = new Answer();
            answer.setPosition(i-1);
            switch (type){
                case Single:                         //multiple choice answer
                    if (answerIsTrue()) {            //Decide whether answer is right or wrong
                        answer.setCorrect(true);
                        answer.setText("Richtig");
                        allAnswersFalse = false;
                    }
                    else {
                        answer.setCorrect(false);
                        answer.setText("Falsch");
                    }
                    break;

                case Multiple:
                    if (answerIsTrue()) {
                        answer.setCorrect(true);
                        answer.setText("Richtig");
                        allAnswersFalse = false;
                    }
                    else {
                        answer.setCorrect(false);
                        answer.setText("Falsch");
                    }
                    break;

                case Gap:
                    answer.setCorrect(true);
                    if (i<2){
                        answer.setText("Lücke");
                    }
                    else {
                        answer.setText("fortuna");
                    }
                    allAnswersFalse = false;
                    break;
                    }

            answerSet.add(answer);
            }
            if (allAnswersFalse) {                                      //make sure that at least 1 answer is correct
                answerSet.get(numberOfAnswers-1).setCorrect(true);
                answerSet.get(numberOfAnswers-1).setText("Richtig");
            }
        return answerSet;
    }

}



