package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Student_Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;
    private Double grade;

    public Student_Course(Student student, Course course, Double grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    public Student_Course(Student student) {
        this.student = student;
    }
}
