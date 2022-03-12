package repositories;

import models.Professor;
import models.Student_Course;

import java.util.List;

public class Student_CourseRepository extends GenericRepositoryImplementation<Student_Course,Long> implements GenericRepository<Student_Course,Long>{

    @Override
    public Student_Course findById(Long id) {
        try(var session = sessionFactory.openSession()){
            return session.find(Student_Course.class,id);
        }
    }

    @Override
    public List<Student_Course> findAll() {
        try(var session = sessionFactory.openSession()){
            return session
                    .createQuery("SELECT sc FROM models.Student_Course sc",Student_Course.class)
                    .list();
        }
    }

    public void truncate(){
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.createNativeQuery("TRUNCATE Student_Course CASCADE ", Student_Course.class)
                    .executeUpdate();
            transaction.commit();
        }
    }

    public void deleteById(Long id) {
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try{
                var student_course = session.createQuery("FROM models.Student_Course sc WHERE sc.id = :id")
                        .setParameter("id",id)
                        .getSingleResult();
                session.delete(student_course);
                transaction.commit();
            }catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }
    }

    public List<Student_Course> findByStudentAndCourseId(Student_Course student_course) {
        try(var session = sessionFactory.openSession()){
            return session
                    .createQuery("SELECT c.name,c.unit FROM models.Student_Course sc INNER JOIN models.Student s ON sc.id = s.id" +
                            " INNER JOIN models.Student s ON sc.id = s.id" +
                            " INNER JOIN models.Course c ON sc.id = c.id" +
                            " WHERE sc.student = :student ",Student_Course.class)
                    .setParameter("student",student_course.getStudent())
                    .list();
        }
    }
}
