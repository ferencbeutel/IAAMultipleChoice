package de.nordakademie.multiplechoice.action;


import de.nordakademie.multiplechoice.model.Lecturer;
import de.nordakademie.multiplechoice.model.Seminar;
import de.nordakademie.multiplechoice.model.Student;

import de.nordakademie.multiplechoice.service.LecturerService;

import de.nordakademie.multiplechoice.service.SeminarService;
import de.nordakademie.multiplechoice.service.StudentService;


import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;



import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.*;

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

    private Set<Student> participants = new HashSet<>();

    //List Initialization
    ArrayList<String> studentFirstNameList = new ArrayList<String>();
    ArrayList<String> studentLastNameList = new ArrayList<String>();
    ArrayList<String> lecturerFirstNameList = new ArrayList<String>();
    ArrayList<String> lecturerLastNameList = new ArrayList<String>();
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
    LocalDate beginDate;
    LocalDate endDate;


    public String initialize(){
        fillNameList();
        createStudent(quantStudents);
        createLecturer(quantLecturer);
        createSeminar(2); //TODO Implement how many seminar should be created
        //TODO Implement Mapping of Students to seminars
        //TODO Implement Test creation and mapping to seminars
        return SUCCESS;
    };

    public Integer nextRandomNumber(int possibilities) { //returns a random number between 0 and 'possibilities'-1
        int randNumber;
        randNumber = (int)(Math.random() * possibilities);
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
            arrayposition = (nextRandomNumber(studentFirstNameList.size())); //Choose random index for studentFirstNameList
            firstName = studentFirstNameList.get(arrayposition);
            arrayposition = (nextRandomNumber(studentLastNameList.size())); //Choose random index for studentLastNameList
            lastName = studentLastNameList.get(arrayposition);
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
            studentService.save(student);
            studentList.add(email);
        }
    };

    public void createLecturer(int number) {
        for (int i = 1; i <= number; i++) {
            Lecturer lecturer = new Lecturer();
            arrayposition = (nextRandomNumber(lecturerFirstNameList.size())); //Choose random index for lecturerFirstNameList
            firstName = lecturerFirstNameList.get(arrayposition);
            arrayposition = (nextRandomNumber(lecturerLastNameList.size())); //Choose random index for lecturerLastNameList
            lastName = lecturerLastNameList.get(arrayposition);
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
            arrayposition = (nextRandomNumber(seminarDescriptionList.size())); //Choose random index for seminarDescriptionList
            seminarDescription = seminarDescriptionList.get(arrayposition);
            maxParticipants = (5 + nextRandomNumber(10));                // Max Participants = 5 + random number between 0...9
            beginDate = LocalDate.now().plusDays((nextRandomNumber(365)+1)); //Startdate = Today + random number of days between 1...365
            endDate = beginDate.plusDays(nextRandomNumber(31));          //Enddate = Startdate + random number of days between 0...30
            arrayposition = (nextRandomNumber(lecturerList.size()));  //Choose random index from LecturerList
            Lecturer lecturer = lecturerService.findByMail(lecturerList.get(arrayposition)); //Select Lecturer
            seminar.setLecturer(lecturer);
            seminar.setName(seminarName);
            seminar.setDescription(seminarDescription);
            seminar.setBeginDate(beginDate);
            seminar.setEndDate(endDate);
            seminar.setMaxParticipants(maxParticipants);
            int enrolledStudents = nextRandomNumber(maxParticipants); //Choose random number of participants
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
            seminarService.createOrUpdate(seminar);
            seminarList.add(seminar);
            participants.clear();
        }
    }


}
