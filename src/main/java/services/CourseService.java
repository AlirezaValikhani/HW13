package services;

import models.Course;
import repositories.CourseRepository;
import repositories.SessionFactorySingleton;

import java.util.List;

public class CourseService implements GenericService<Course,Long>{
    private CourseRepository courseRepository;

    public CourseService() {
        this.courseRepository = new CourseRepository();
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void update(Course course) {
        courseRepository.update(course);
    }

    @Override
    public void delete(Course course) {
        courseRepository.delete(course);
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }
}
