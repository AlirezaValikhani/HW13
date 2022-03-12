package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee extends Person{
    private Double salary;

    public Employee(Long id, String userName, String password, String firstName, String lastName, String nationalCode, String phoneNumber, Double salary) {
        super(id, userName, password, firstName, lastName, nationalCode, phoneNumber);
        this.salary = salary;
    }

    public Employee(String userName, String password, String firstName, String lastName, String nationalCode, String phoneNumber, Double salary) {
        super(userName, password, firstName, lastName, nationalCode, phoneNumber);
        this.salary = salary;
    }

    public Employee(Long id) {
        super(id);
    }

    public Employee(String userName) {
        super(userName);
    }

    @Override
    public String toString() {
        return super.toString() + "\nSalary : " + salary + "\n-------------------" ;
    }
}
