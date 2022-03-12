package repositories;

import models.Student;
import models.Term;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {
    private static SessionFactory sessionFactory;
    private static StudentRepository studentRepository;
    private static TermRepository termRepository;

    @BeforeAll
    static void connection(){
        sessionFactory = SessionFactorySingleton.getInstance();
        studentRepository = new StudentRepository();
    }

    @Test
    void saveTest(){
        //Arrange
        Student s = new Student(0L,"s","f","r","e","1","2",null,new HashSet<>());
        //Act
        Student s1 = studentRepository.save(s);
        //Assert
        assertNotNull(s1);
    }

    @Test
    void updateTest(){
        //Arrange
        Student student = new Student(null,"ali1234","1234","ali","rad","f","d",null,new HashSet<>());
        Student s = studentRepository.save(student);

        //Act
        Student studentUpdate = new Student(s.getId(),"e","e","e","e","f","d",null,new HashSet<>());
        Student updatedStudent = studentRepository.update(studentUpdate);
        //Assert
        assertNotNull(updatedStudent);
        assertEquals("e",updatedStudent.getUserName());
        assertEquals("e",updatedStudent.getPassword());
        assertEquals(s.getId(),updatedStudent.getId());
    }

    @Test
    void deleteTest(){
        //Arrange
        Student student = new Student(null,"ali1234","1234","ali","rad","f","d",null,new HashSet<>());
        Student s = studentRepository.save(student);
        student.setId(s.getId());
        //Act
        studentRepository.delete(student);
        //Assert
        assertNull(studentRepository.findById(student.getId()));
        assertEquals(0,studentRepository.findAll().size());
    }

    @Test
    void deleteByIdTest(){
        //Arrange
        Student student = new Student(null,"ali1234","1234","ali","rad","f","d",null,new HashSet<>());
        Student s = studentRepository.save(student);
        student.setId(s.getId());
        //Act
        studentRepository.deleteById(student.getId());
        //Assert
        assertNull(studentRepository.findById(student.getId()));
        assertEquals(0,studentRepository.findAll().size());
    }

    @Test
    void findByIdTest(){
        //Arrange
        Student student = new Student(null,"ali1234","1234","ali","rad","f","d",null,new HashSet<>());
        Student s = studentRepository.save(student);
        student.setId(s.getId());
        //Act
        studentRepository.findById(student.getId());
        //Assert
        assertNotNull(student);
        assertNotEquals(0,student.getId());
        assertEquals(1,studentRepository.findAll().size());
    }
    //مشکلمو باید بپرسم
    /*@Test
    void findByIdTest(){
        //Arrange
        Student student = new Student(null,"ali1234","1234","ali","rad","f","d",null,new HashSet<>());
        Term term = new Term(null,"2");
        student.setTerm(term);
        Student s = studentRepository.save(student);
        student.setId(s.getId());
        //Act
        studentRepository.findById(student.getId());
        //Assert
        assertNotNull(student);
        assertNotEquals(0,student.getId());
        assertEquals(1,studentRepository.findAll().size());
    }*/

    @Test
    void findAllTest(){
        //Arrange
        Student student = new Student(null,"ali1234","1234","ali","rad","f","d",null,new HashSet<>());
        Student s = studentRepository.save(student);
        student.setId(s.getId());
        //Act
        studentRepository.findAll();
        //Assert
        assertEquals(student.getUserName(),studentRepository.findAll().get(0).getUserName());
        assertNotEquals(0,studentRepository.findAll());
    }

    @AfterEach
    public void cleanUp(){
        studentRepository.truncate();
        /*termRepository.truncate();*/
    }
}