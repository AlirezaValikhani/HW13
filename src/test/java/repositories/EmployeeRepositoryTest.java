package repositories;

import models.Employee;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRepositoryTest {
    private static SessionFactory sessionFactory;
    private static EmployeeRepository employeeRepository;

    @BeforeAll
    static void connection(){
        sessionFactory = SessionFactorySingleton.getInstance();
        employeeRepository = new EmployeeRepository();
    }

    @Test
    void saveTest(){
        //Arrange
        Employee employee = new Employee(null,"w","w","w","w","w","w",1d);
        // Act
        Employee savedEmployee = employeeRepository.save(employee);
        //Assert
        assertNotNull(savedEmployee);
    }

    @Test
    void updateTest(){
        //Arrange
        Employee employee = new Employee(null,"a","a","a","a","a","a",1d);
        Employee employee1 = employeeRepository.save(employee);
        employee.setId(employee1.getId());
        //Act
        Employee employee2 = new Employee(employee.getId(),"a1","a1","a1","a1","a1","a1",11d);
        Employee updatedEmployee = employeeRepository.update(employee2);
        //Assert
        assertNotNull(updatedEmployee);
        assertEquals("a1",updatedEmployee.getUserName());
        assertEquals("a1",updatedEmployee.getPassword());
        assertEquals(employee.getId(),updatedEmployee.getId());
    }

    @Test
    void deleteTest(){
        //Arrange
        Employee employee = new Employee(null,"a","a","a","a","a","a",1d);
        Employee employee1 = employeeRepository.save(employee);
        employee.setId(employee1.getId());
        //Act
        employeeRepository.delete(employee);
        //Assert
        assertNull(employeeRepository.findById(employee.getId()));
        assertEquals(0,employeeRepository.findAll().size());
    }

    @Test
    void deleteByIdTest(){
        //Arrange
        Employee employee = new Employee(null,"a","a","a","a","a","a",1d);
        Employee employee1 = employeeRepository.save(employee);
        employee.setId(employee1.getId());
        //Act
        employeeRepository.deleteById(employee.getId());
        //Assert
        assertNull(employeeRepository.findById(employee.getId()));
        assertEquals(0,employeeRepository.findAll().size());
    }

    @Test
    void findByIdTest(){
        //Arrange
        Employee employee = new Employee(null,"a","a","a","a","a","a",1d);
        Employee employee1 = employeeRepository.save(employee);
        employee.setId(employee1.getId());
        //Act
        employeeRepository.findById(employee.getId());
        //Assert
        assertNotNull(employee);
        assertNotEquals(0,employee.getId());
        assertEquals(1,employeeRepository.findAll().size());
    }

    @Test
    void findAllTest(){
        //Arrange
        Employee employee = new Employee(null,"a","a","a","a","a","a",1d);
        Employee employee1 = employeeRepository.save(employee);
        employee.setId(employee1.getId());
        //Act
        employeeRepository.findAll();
        //Assert
        assertEquals(employee.getUserName(),employeeRepository.findAll().get(0).getUserName());
        assertNotEquals(0,employeeRepository.findAll());
    }

    @Test
    public void findByUserName(){
        //Arrange
        Employee employee = new Employee(null,"a","a","a","a","a","a",1d);
        Employee employee1 = employeeRepository.save(employee);
        employee.setId(employee1.getId());
        //Act
        Employee employee2 = employeeRepository.findByUserName(employee.getUserName());
        //Assert
        assertNotNull(employee2);
        assertNotEquals(0,employee.getId());
        assertEquals(1,employeeRepository.findAll().size());
    }

    @AfterEach
    public void cleanUp(){
        employeeRepository.truncate();
    }

}