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
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String name;
    private Integer unit;

    @OneToMany(mappedBy = "course")
    private Set<Student_Course> student_courses;

    public Course(String name, Integer unit) {
        this.name = name;
        this.unit = unit;
    }

    public Course(Long id, String name, Integer unit) {
        this.id = id;
        this.name = name;
        this.unit = unit;
    }

    public Course(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "-------------------\nID : " + id + "\nCourse name : " + name + "\nunit : " + unit + "\n-------------------\n";
    }
}
