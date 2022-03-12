package services;

import models.Student_Course;
import repositories.Student_CourseRepository;

import java.util.List;

public class StudentCourseService implements GenericService<Student_Course,Long>{
    private Student_CourseRepository student_courseRepository;

    public StudentCourseService() {
        this.student_courseRepository = new Student_CourseRepository();
    }

    @Override
    public Student_Course save(Student_Course student_course) {
        return student_courseRepository.save(student_course);
    }

    @Override
    public void update(Student_Course student_course) {
        student_courseRepository.update(student_course);
    }

    @Override
    public void delete(Student_Course student_course) {
        student_courseRepository.delete(student_course);
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Student_Course findById(Long id) {
        return student_courseRepository.findById(id);
    }

    @Override
    public List<Student_Course> findAll() {
        return student_courseRepository.findAll();
    }

    public List<Student_Course> findByStudentAndCourseId(Student_Course student_course){
        return student_courseRepository.findByStudentAndCourseId(student_course);
    }
}
