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
        if (number <= 7){ //
            return true;
        }
        return false;
    }

    public void fillNameList(){
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
            email = (firstName + "." + lastName + "@nordakademie.de");
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
            email = (firstName + "." + lastName + "@nordakademie.de");
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
        testBeginDate =  seminarBeginDate.plusDays(nextRandomNumber(31));
        testEndDate = testBeginDate.plusDays(nextRandomNumber(40));
        test.setBeginDate(testBeginDate);
        test.setEndDate(testEndDate);
        testDuration = LocalTime.parse("00:00",durationFormatter); //Todo funzt noch nicht
        testDuration.plusMinutes(nextRandomNumber(120));
        test.setDuration(testDuration);
        test.setMinScore(nextRandomNumber(80));
        int creditPoint = nextRandomNumber(3);
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
        int evalType = nextRandomNumber(2);
        switch (evalType){
            case 0:
                test.setEvaluationType(EvaluationType.FATAL);
                break;
            case 1:
                test.setEvaluationType(EvaluationType.SUBSTRACT);
        }
        //QuestionSet qs = createQuestions(10); //Todo question creation
        //test.setQuestions(qs);
        return test;
    }


}
