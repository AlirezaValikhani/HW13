package services;

import models.Student;
import repositories.StudentRepository;

import java.util.List;

public class StudentService implements GenericService<Student,Long>{
    private StudentRepository studentRepository;

    public StudentService() {
        this.studentRepository = new StudentRepository();
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void update(Student student) {
        studentRepository.update(student);
    }

    @Override
    public void delete(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public void deleteById(Long aLong) {

    }

   /* @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }*/

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findByUserName(String userName){
        return studentRepository.findByUserName(userName);
    }
}
