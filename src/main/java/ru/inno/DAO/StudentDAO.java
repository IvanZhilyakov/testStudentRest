package ru.inno.DAO;



import ru.inno.entities.Student;

import java.util.List;

public interface StudentDAO {
    public List<Student> findAll();
    public Student save(Student student);
    public Student getById(Integer id);
}
