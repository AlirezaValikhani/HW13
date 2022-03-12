package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Student extends Person{
    @ManyToOne
    private Term term;

    @OneToMany(mappedBy = "student")
    private Set<Student_Course> student_courses;


    public Student(Long id, String userName, String password, String firstName, String lastName, String nationalCode, String phoneNumber, Term term, Set<Student_Course> student_courses) {
        super(id, userName, password, firstName, lastName, nationalCode, phoneNumber);
        this.term = term;
        this.student_courses = student_courses;
    }

    public Student(Long id) {
        super(id);
    }

    public Student(Long id, String userName, String password, String firstName, String lastName, String nationalCode, String phoneNumber) {
        super(id, userName, password, firstName, lastName, nationalCode, phoneNumber);
    }

    public Student(String userName, String password, String firstName, String lastName, String nationalCode, String phoneNumber, Term term, Set<Student_Course> student_courses) {
        super(userName, password, firstName, lastName, nationalCode, phoneNumber);
        this.term = term;
        this.student_courses = student_courses;
    }

    @Override
    public String toString() {
        return super.toString() + "\n-------------------";
    }
}
