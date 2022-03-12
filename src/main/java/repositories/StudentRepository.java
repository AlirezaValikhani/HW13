package repositories;

import models.Course;
import models.Student;

import java.util.List;

public class StudentRepository extends GenericRepositoryImplementation<Student,Long> implements GenericRepository<Student,Long>{

    @Override
    public Student findById(Long id) {
        try(var session = sessionFactory.openSession()){
            return session.find(Student.class, id);
        }
    }

    @Override
    public List<Student> findAll() {
        try(var session = sessionFactory.openSession()){
            return session
                    .createQuery("SELECT s FROM models.Student s",Student.class)
                    .list();
        }
    }

    public void truncate(){
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.createNativeQuery("TRUNCATE Student CASCADE ", Student.class)
                    .executeUpdate();
            transaction.commit();
        }
    }

    public void deleteById(Long id) {
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try{
                var student = session.createQuery("FROM models.Student s WHERE s.id = :id")
                        .setParameter("id",id)
                        .getSingleResult();
                session.delete(student);
                transaction.commit();
            }catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }
    }

    public Student findByUserName(String userName) {
        try(var session = sessionFactory.openSession()){
            return session.find(Student.class, userName);
        }
    }
}
