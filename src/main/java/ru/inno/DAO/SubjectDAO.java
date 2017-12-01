package ru.inno.DAO;

import ru.inno.entities.Student;
import ru.inno.entities.Subject;

import java.util.List;

public interface SubjectDAO {
    public Subject save(Subject subject);
    public List<Subject> findAll();
    public List<Subject> getAllSubjectsExecptStudents(Integer studentId);
    public Subject getById(Integer id);
}
