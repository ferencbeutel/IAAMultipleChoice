package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.model.Student;
import de.nordakademie.multiplechoice.model.User;
import de.nordakademie.multiplechoice.service.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Ferenc on 19.10.2016.
 */
public class AddQuestionAction extends BaseAction {
    @Autowired
    StudentService studentService;

    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;

    @Getter
    @Setter
    private User user;
    private String addQuestion(){
        return SUCCESS;
    }
    public String register() {
        final String uuid = "f";
        user.setRegToken(uuid);
        user.setRegComplete(false);
        final Student student = new Student();
        student.setUser(user);
        studentService.saveStudent(student);
        return SUCCESS;
    }


}
