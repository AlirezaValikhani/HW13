package repositories;

import models.Course;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.util.List;

public class CourseRepository extends GenericRepositoryImplementation<Course,Long> implements GenericRepository<Course,Long>{

    @Override
    public Course findById(Long id) {
        try(var session = sessionFactory.openSession()){
            return session.find(Course.class, id);
        }
    }

    @Override
    public List<Course> findAll() {
        try(var session = sessionFactory.openSession()){
            return session
                    .createQuery("SELECT c FROM models.Course c",Course.class)
                    .list();
        }
    }

    public void truncate(){
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.createNativeQuery("TRUNCATE Course CASCADE ",Course.class)
                    .executeUpdate();
            transaction.commit();
        }
    }

    public void deleteById(Long id) {
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try{
                var course = session.createQuery("FROM models.Course c WHERE c.id = :id")
                        .setParameter("id",id)
                        .getSingleResult();
                session.delete(course);
                transaction.commit();
            }catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }
    }
}
