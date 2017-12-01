package ru.inno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.inno.DAO.StudentDAOImpl;
import ru.inno.DAO.SubjectDAOImpl;
import ru.inno.entities.Student;
import ru.inno.entities.Subject;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    SubjectDAOImpl subjectDAO;
    @Autowired
    StudentDAOImpl studentDAO;


    @RequestMapping(value = "/getAllSubjects/{studentId}", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public List<Subject> getSubjects(@PathVariable("studentId") Integer studentID){
        List<Subject> list = subjectDAO.getAllSubjectsExecptStudents(studentID);
        return list;
    }

    @RequestMapping(value = "/saveChanges",
            method = RequestMethod.POST)
    public void saveChanges(@RequestParam(name = "student") Integer student,
                            @RequestParam(name = "subject") Integer subject){

       Student st = studentDAO.getById(student);
       Subject sub = subjectDAO.getById(subject);

        st.addSubject(sub);
        studentDAO.save(st);
    }

}
