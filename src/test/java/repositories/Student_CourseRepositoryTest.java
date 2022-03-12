package repositories;

import models.Student_Course;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class Student_CourseRepositoryTest {
    private static SessionFactory sessionFactory;
    private static Student_CourseRepository student_courseRepository;
    private static StudentRepository studentRepository;
    private static CourseRepository courseRepository;

    @BeforeAll
    static void connection(){
        sessionFactory = SessionFactorySingleton.getInstance();
        student_courseRepository = new Student_CourseRepository();
    }

    @Test
    void saveTest(){
        //Arrange
        Student_Course student_course = new Student_Course(null,null,null,20d);
        // Act
        Student_Course student_course1 = student_courseRepository.save(student_course);
        //Assert
        assertNotNull(student_course1);
    }

    @Test
    void updateTest(){
        //Arrange
        Student_Course student_course = new Student_Course(null,null,null,20d);
        Student_Course student_course1 = student_courseRepository.save(student_course);
        student_course.setId(student_course1.getId());
        //Act
        Student_Course student_course2 = new Student_Course(student_course.getId(),null,null,10d);
        Student_Course updatedStudent_Course = student_courseRepository.update(student_course2);
        //Assert
        assertNotNull(updatedStudent_Course);
        assertEquals(10d,updatedStudent_Course.getGrade());
        assertEquals(student_course.getId(),updatedStudent_Course.getId());
    }

    @Test
    void deleteTest(){
        //Arrange
        Student_Course student_course = new Student_Course(null,null,null,20d);
        Student_Course student_course1 = student_courseRepository.save(student_course);
        student_course.setId(student_course1.getId());
        //Act
        student_courseRepository.delete(student_course);
        //Assert
        assertNull(student_courseRepository.findById(student_course.getId()));
        assertEquals(0,student_courseRepository.findAll().size());
    }

    @Test
    void deleteByIdTest(){
        //Arrange
        Student_Course student_course = new Student_Course(null,null,null,20d);
        Student_Course student_course1 = student_courseRepository.save(student_course);
        student_course.setId(student_course1.getId());
        //Act
        student_courseRepository.deleteById(student_course.getId());
        //Assert
        assertNull(student_courseRepository.findById(student_course.getId()));
        assertEquals(0,student_courseRepository.findAll().size());
    }

    @Test
    void findByIdTest(){
        //Arrange
        Student_Course student_course = new Student_Course(null,null,null,20d);
        Student_Course student_course1 = student_courseRepository.save(student_course);
        student_course.setId(student_course1.getId());
        //Act
        student_courseRepository.findById(student_course.getId());
        //Assert
        assertNotNull(student_course);
        assertNotEquals(0,student_course.getId());
        assertEquals(1,student_courseRepository.findAll().size());
    }

    @Test
    void findAllTest(){
        //Arrange
        Student_Course student_course = new Student_Course(null,null,null,20d);
        Student_Course student_course1 = student_courseRepository.save(student_course);
        student_course.setId(student_course1.getId());
        //Act
        student_courseRepository.findAll();
        //Assert
        assertEquals(student_course.getGrade(),student_courseRepository.findAll().get(0).getGrade());
        assertNotEquals(0,student_courseRepository.findAll());
    }

    @AfterEach
    public void cleanUp(){
        student_courseRepository.truncate();
    }

}