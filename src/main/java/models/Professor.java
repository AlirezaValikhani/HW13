package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Professor extends Person{
    private Double salary;
    @ManyToOne
    private Course course;

    public Professor(Long id, String userName, String password, String firstName, String lastName, String nationalCode, String phoneNumber, Double salary, Course course) {
        super(id, userName, password, firstName, lastName, nationalCode, phoneNumber);
        this.salary = salary;
        this.course = course;
    }

    public Professor(String userName, String password, String firstName, String lastName, String nationalCode, String phoneNumber, Double salary, Course course) {
        super(userName, password, firstName, lastName, nationalCode, phoneNumber);
        this.salary = salary;
        this.course = course;
    }

    public Professor(Long id) {
        super(id);
    }

    @Override
    public String toString() {
        return super.toString() + "\nSalary : " + salary + "\nCourse name : "
                + course.getName() + "\nCourse unit : " + course.getUnit() +"\n-------------------" ;
    }
}
