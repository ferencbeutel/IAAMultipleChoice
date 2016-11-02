package de.nordakademie.multiplechoice.action;


import de.nordakademie.multiplechoice.model.Lecturer;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.Student;

import de.nordakademie.multiplechoice.service.LecturerService;

import de.nordakademie.multiplechoice.service.SeminarService;
import de.nordakademie.multiplechoice.service.StudentService;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    ArrayList<String> studentFirstNameList = new ArrayList<String>();
    ArrayList<String> studentLastNameList = new ArrayList<String>();
    ArrayList<String> lecturerFirstNameList = new ArrayList<String>();
    ArrayList<String> lecturerLastNameList = new ArrayList<String>();
    ArrayList<String> userEmailList = new ArrayList<String>();
    ArrayList<String> seminarNameList = new ArrayList<String>();
    ArrayList<String> seminarDescriptionList = new ArrayList<String>();
    ArrayList<String> lecturerList = new ArrayList<String>();

    int arrayposition;
    int maxParticipants;
    String firstName;
    String lastName;
    String email;
    String seminarName;
    String seminarDescription;

    public String initialize(){
        fillNameList();
        createStudent(quantStudents);
        createLecturer(quantLecturer);
        createSeminar(10);
        return SUCCESS;
    };

    public Integer nextRandomNumber(int possibilities) {
        int randNumber;
        randNumber = (int)(Math.random() * possibilities) + 1;
        return randNumber;
    }


    public void fillNameList(){
        studentFirstNameList.add("Anna");
        studentFirstNameList.add("Heinrich");
        studentFirstNameList.add("Max");
        studentFirstNameList.add("Tom");
        studentFirstNameList.add("Joshua");
        studentFirstNameList.add("Mark");
        studentFirstNameList.add("Finn");
        studentFirstNameList.add("Lisa");
        studentFirstNameList.add("Svenja");
        studentFirstNameList.add("Johanna");
        studentFirstNameList.add("Maxi");
        studentLastNameList.add("Schmidt");
        studentLastNameList.add("Schmröder");
        studentLastNameList.add("Schmacko");
        studentLastNameList.add("Schmissel");
        studentLastNameList.add("Schmalz");
        studentLastNameList.add("Schwup");
        studentLastNameList.add("Schmipmann");
        studentLastNameList.add("Schniepel");
        studentLastNameList.add("Schwarso");
        lecturerFirstNameList.add("Jürgen");
        lecturerFirstNameList.add("Anna");
        lecturerFirstNameList.add("Heinrich");
        lecturerFirstNameList.add("Max");
        lecturerFirstNameList.add("Tom");
        lecturerFirstNameList.add("Joshua");
        lecturerFirstNameList.add("Mark");
        lecturerFirstNameList.add("Finn");
        lecturerFirstNameList.add("Lisa");
        lecturerFirstNameList.add("Svenja");
        lecturerFirstNameList.add("Johanna");
        lecturerFirstNameList.add("Maxi");
        lecturerLastNameList.add("Schmidt");
        lecturerLastNameList.add("Schmröder");
        lecturerLastNameList.add("Schmacko");
        lecturerLastNameList.add("Schmissel");
        lecturerLastNameList.add("Schmalz");
        lecturerLastNameList.add("Schwup");
        lecturerLastNameList.add("Schmipmann");
        lecturerLastNameList.add("Schniepel");
        lecturerLastNameList.add("Schwarso");
        lecturerLastNameList.add("Schololwol");
        seminarNameList.add("Seminar1");
        seminarNameList.add("Seminar2");
        seminarNameList.add("Seminar3");
        seminarNameList.add("Seminar4");
        seminarNameList.add("Seminar5");
        seminarDescriptionList.add("SeminarBeschreibung1");
        seminarDescriptionList.add("SeminarBeschreibung2");
        seminarDescriptionList.add("SeminarBeschreibung3");
        seminarDescriptionList.add("SeminarBeschreibung4");
        seminarDescriptionList.add("SeminarBeschreibung5");
    }

    public void createStudent(int number) {
        for (int i = 1; i <= number; i++) {
            Student student = new Student();
            arrayposition = (nextRandomNumber(studentFirstNameList.size()-1)-1);
            firstName = studentFirstNameList.get(arrayposition);
            arrayposition = (nextRandomNumber(studentLastNameList.size()-1)-1);
            lastName = studentLastNameList.get(arrayposition);
            email = (firstName + "." + lastName + "@nordakademie.de");
            if (userEmailList.contains(email)){
                number ++;
                continue;
            }
            student.setName(firstName);
            student.setSurName(lastName);
            student.setRegComplete(true);
            student.setEmail(email);
            student.setPassword("Passwort1");
            studentService.save(student);
            userEmailList.add(email);
        }
    };

    public void createLecturer(int number) {
        for (int i = 1; i <= number; i++) {
            Lecturer lecturer = new Lecturer();
            arrayposition = (nextRandomNumber(lecturerFirstNameList.size()-1)-1);
            firstName = lecturerFirstNameList.get(arrayposition);
            arrayposition = (nextRandomNumber(lecturerLastNameList.size()-1)-1);
            lastName = lecturerLastNameList.get(arrayposition);
            email = (firstName + "." + lastName + "@nordakademie.de");
            if (userEmailList.contains(email)){
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
            arrayposition = (nextRandomNumber(seminarNameList.size()-1)-1);
            seminarName = seminarNameList.get(arrayposition);
            arrayposition = (nextRandomNumber(seminarDescriptionList.size()-1)-1);
            seminarDescription = seminarDescriptionList.get(arrayposition);
            maxParticipants = (20 + nextRandomNumber(10));
            arrayposition = (nextRandomNumber(lecturerList.size()-1)-1);
            Lecturer lecturer = lecturerService.findByMail(lecturerList.get(arrayposition));
            seminar.setLecturer(lecturer);
            seminar.setName(seminarName);
            seminar.setDescription(seminarDescription);
            seminar.setBeginDate(LocalDate.parse("2017-01-01", dateFormatter));
            seminar.setEndDate(LocalDate.parse("2017-02-17", dateFormatter));
            seminar.setMaxParticipants(maxParticipants);
            seminarService.createOrUpdate(seminar);
        }
    }

}
