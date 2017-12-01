package ru.inno.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subjects")
public class Subject {
    private int subjectID;
    private String subjectName;
    @JsonIgnore
    private Set<Student> students = new HashSet<>();

    public Subject(int subjectID, String subjectName) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
    }

    public Subject() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    @Column(name = "subject_name")
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "students_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (subjectID != subject.subjectID) return false;
        return subjectName.equals(subject.subjectName);
    }

    @Override
    public int hashCode() {
        int result = subjectID;
        result = 31 * result + subjectName.hashCode();
        return result;
    }
}
