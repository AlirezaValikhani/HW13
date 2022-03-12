package repositories;

import models.Course;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class CourseRepositoryTest {
    private static SessionFactory sessionFactory;
    private static CourseRepository courseRepository;

    @BeforeAll
     static void connection(){
        sessionFactory = SessionFactorySingleton.getInstance();
        courseRepository = new CourseRepository();
    }

    @Test
    void connectionTest(){
        //Arrange

        //Act

        //Assert
        assertDoesNotThrow(() -> sessionFactory = SessionFactorySingleton.getInstance());
    }

    @Test
    void saveTest(){
        //Arrange
        Course course = new Course(null,"math",2,new HashSet<>());
        //Act
        Course returnCourse = courseRepository.save(course);
        //Assert
        assertNotNull(returnCourse);
        assertNotEquals(0,returnCourse.getId());
        assertEquals(returnCourse.getId(),course.getId());
    }

    @Test
    void updateTest(){
        //Arrange
        Course course = new Course(null,"math",2,new HashSet<>());
        Course saveCourse = courseRepository.save(course);
        course.setId(saveCourse.getId());
        //Act
        Course course2 = new Course(course.getId(),"physics",3,new HashSet<>());
        Course updateCourse = courseRepository.update(course2);
        //Assert
        assertNotNull(updateCourse);
        assertEquals("physics",updateCourse.getName());
        assertEquals(3,updateCourse.getUnit());
        assertEquals(course.getId(),saveCourse.getId());
    }

    @Test
    void deleteTest(){
        //Arrange
        Course course = new Course(null,"math",2,new HashSet<>());
        Course course1 = courseRepository.save(course);
        course.setId(course1.getId());
        //Act
        courseRepository.delete(course);
        //Assert
        assertNull(courseRepository.findById(course.getId()));
        assertEquals(0,courseRepository.findAll().size());
    }

    @Test
    void deleteByIdTest(){
        //Arrange
        Course course = new Course(null,"math",2,new HashSet<>());
        Course course1 = courseRepository.save(course);
        course.setId(course1.getId());
        //Act
        courseRepository.deleteById(course.getId());
        //Assert
        assertNull(courseRepository.findById(course.getId()));
        assertEquals(0,courseRepository.findAll().size());
    }

    @Test
    void findByIdTest(){
        //Arrange
        Course course = new Course(null,"math",2,new HashSet<>());
        Course course1 = courseRepository.save(course);
        course.setId(course1.getId());
        //Act
        courseRepository.findById(course.getId());
        //Assert
        assertNotNull(course);
        assertNotEquals(0,course.getId());
        assertEquals(1,courseRepository.findAll().size());
    }

    @Test
    void findAllTest(){
        //Arrange
        Course course = new Course(null,"math",2,new HashSet<>());
        Course returnedCourse = courseRepository.save(course);
        course.setId(returnedCourse.getId());
        //Act
        courseRepository.findAll();
        //Assert
        assertEquals(course.getName(),courseRepository.findAll().get(0).getName());
        assertNotEquals(0,courseRepository.findAll());
    }

    @AfterEach
    public void cleanUp(){
        courseRepository.truncate();
    }
}