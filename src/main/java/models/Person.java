package models;

import lombok.*;
import org.hibernate.annotations.DiscriminatorFormula;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    @Column(length = 10)
    private String nationalCode;
    @Column(length = 11)
    private String phoneNumber;


    public Person(Long id) {
        this.id = id;
    }

    public Person(String userName, String password, String firstName, String lastName, String nationalCode, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.phoneNumber = phoneNumber;
    }

    public Person(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "-------------------\nID : " + id + "\nuser name : " + userName +
                "\nPassword : " + password + "\nFirst name : " + firstName +
                "\nLast name : " + lastName + "\nNational code : " + nationalCode +
                "\nPhone number : " + phoneNumber;
    }
}
