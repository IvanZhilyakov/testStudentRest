package ru.inno.DAO;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.inno.entities.*;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {
    @Autowired
    private SessionFactory factory;


    @Override
    public List<Student> findAll() {

        Session session = factory.openSession();
        Query query = session.createQuery("select distinct s from Student s");
        List<Student> ls = query.list();
        session.close();
        return ls;
    }

    @Override
    public Student save(Student student) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(student);
        session.getTransaction().commit();
        session.close();
        return student;
    }

    @Override
    public Student getById(Integer id){
        Session session = factory.openSession();
        Query query = session.createQuery("select s from Student s where s.id=:student_id");
        query.setParameter("student_id", id);
        Student student = (Student) query.list().get(0);
        session.close();
        return student;
    }
}
