package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String termNumber;
    @OneToMany(mappedBy = "term")
    private Set<Student> student;

    public Term(Long id, String termNumber, Set<Student> student) {
        this.id = id;
        this.termNumber = termNumber;
        this.student = student;
    }

    public Term() {
    }

    public Term(String termNumber) {
        this.termNumber = termNumber;
    }
}
