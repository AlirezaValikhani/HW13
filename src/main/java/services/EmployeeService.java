package services;

import models.Course;
import models.Employee;
import repositories.CourseRepository;
import repositories.EmployeeRepository;

import java.util.List;

public class EmployeeService implements GenericService<Employee,Long>{
    private EmployeeRepository employeeRepository;

    public EmployeeService() {
        this.employeeRepository = new EmployeeRepository();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeRepository.update(employee);
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public void deleteById(Long aLong) {

    }

    /*@Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }*/

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findByUserName(String userName){
        return employeeRepository.findByUserName(userName);
    }
}
