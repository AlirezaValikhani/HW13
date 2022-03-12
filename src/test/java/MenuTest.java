import models.Employee;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.*;
import services.*;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    SessionFactory sessionFactory;
    StudentRepository studentRepository;
    ProfessorRepository professorRepository;
    EmployeeRepository employeeRepository;
    CourseRepository courseRepository;
    Student_CourseRepository student_courseRepository;
    TermRepository termRepository;
    Menu menu;

    @BeforeEach
    public void initialize(){
        sessionFactory = SessionFactorySingleton.getInstance();
        studentRepository = new StudentRepository();
        professorRepository = new ProfessorRepository();
        employeeRepository = new EmployeeRepository();
        courseRepository = new CourseRepository();
        student_courseRepository = new Student_CourseRepository();
        termRepository = new TermRepository();
        menu = new Menu();
    }

    @Test
    void searchByEmployeeUserName() {
        /*Employee employee = new Employee(null,"admin","admin","e","e","1",1d);
        Employee employee1 = employeeRepository.save(employee);
        employee.setId(employee1.getId());

        menu.searchByEmployeeUserName();

        assertNotNull(returnedEmployee);
        assertEquals(employee.getUserName(),returnedEmployee.getUserName());
        assertEquals(employee.getId(),returnedEmployee.getId());*/
    }

    @Test
    void employeeLogIn() {
    }

    @Test
    void employeeMenu() {
    }

    @Test
    void studentSection() {
    }

    @Test
    void addStudent() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void editStudent() {
    }

    @Test
    void seeAllStudents() {
    }

    @Test
    void professorSection() {
    }

    @Test
    void addProfessor() {
    }

    @Test
    void deleteProfessor() {
    }

    @Test
    void editProfessor() {
    }

    @Test
    void seeAllProfessor() {
    }

    @Test
    void employeeSection() {
    }

    @Test
    void addEmployee() {
    }

    @Test
    void deleteEmployee() {
    }

    @Test
    void editEmployee() {
    }

    @Test
    void seeAllEmployees() {
    }

    @Test
    void courseSection() {
    }

    @Test
    void addCourse() {
    }

    @Test
    void deleteCourse() {
    }

    @Test
    void editCourse() {
    }

    @Test
    void seeAllCourses() {
    }

    @Test
    void searchByProfessorUserName() {
    }

    @Test
    void professorRegister() {
    }

    @Test
    void professorLogIn() {
    }

    @Test
    void professorMenu() {
    }

    @Test
    void searchByStudentUserName() {
    }

    @Test
    void studentLogIn() {
    }

    @Test
    void studentMenu() {
    }

    @Test
    void seeProfile() {
    }

    @Test
    void seeCourseList() {
    }

    @Test
    void selectUnit() {
    }

    @Test
    void seeYourCourses() {
    }
}