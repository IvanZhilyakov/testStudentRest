package ru.inno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.inno.DAO.StudentDAOImpl;
import ru.inno.DAO.SubjectDAO;
import ru.inno.DAO.SubjectDAOImpl;
import ru.inno.entities.Student;
import org.springframework.http.MediaType;
import ru.inno.entities.Subject;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentDAOImpl studentDAO;

    @Autowired
    SubjectDAOImpl subjectDAO;


    @RequestMapping(value = "/getAllStudents",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public List<Student> getAllStudents(){
        List<Student> list = studentDAO.findAll();
        return   list;
    }

}
