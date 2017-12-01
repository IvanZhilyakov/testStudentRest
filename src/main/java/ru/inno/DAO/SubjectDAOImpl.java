package ru.inno.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.inno.entities.Student;
import ru.inno.entities.Subject;

import java.util.List;

@Repository
public class SubjectDAOImpl implements SubjectDAO {
    @Autowired
    private SessionFactory factory;

    @Override
    public Subject save(Subject subject) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(subject);
        session.getTransaction().commit();
        session.close();
        return subject;
    }

    @Override
    public List<Subject> findAll() {
        Session session = factory.openSession();
        return session.createCriteria(Subject.class).list();
    }

    @Override
    public List<Subject> getAllSubjectsExecptStudents(Integer studentId){
        Session session = factory.openSession();
        Query query = session.createQuery("select s.id from Subject s left join s.students st " +
                " where st.id=:student_id");
        query.setParameter("student_id", studentId);
        List<Integer> ids =  query.list();

        Query query1;
        if (ids.size()==0){
            query1=session.createQuery("select s from Subject s ");
        }
        else {
          query1=session.createQuery("select s from Subject s where  s.id not in (:ids)");
          query1.setParameterList("ids",  ids);
        }

        return query1.list();
    }

    @Override
    public Subject getById(Integer id){
        Session session = factory.openSession();
        Query query = session.createQuery("select s from Subject s where s.id=:subject_id");
        query.setParameter("subject_id", id);
        Subject subject = (Subject) query.list().get(0);
        session.close();
        return subject;
    }


}
