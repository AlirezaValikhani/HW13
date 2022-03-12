package repositories;

import models.Employee;
import models.Professor;

import java.util.List;

public class ProfessorRepository extends GenericRepositoryImplementation<Professor,Long> implements GenericRepository<Professor,Long>{

    @Override
    public Professor findById(Long id) {
        try(var session = sessionFactory.openSession()){
            return session.find(Professor.class,id);
        }
    }

    @Override
    public List<Professor> findAll() {
        try(var session = sessionFactory.openSession()){
            return session
                    .createQuery("SELECT p FROM models.Professor p",Professor.class)
                    .list();
        }
    }

    public void truncate(){
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.createNativeQuery("TRUNCATE Professor CASCADE ", Professor.class)
                    .executeUpdate();
            transaction.commit();
        }
    }

    public void deleteById(Long id) {
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try{
                session.createQuery("DELETE FROM models.Professor p WHERE p.id = :id")
                        .setParameter("id",id)
                        .getSingleResult();
                transaction.commit();
            }catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }
    }

    public Professor findByUserName(String userName) {
        try(var session = sessionFactory.openSession()){
            return session.find(Professor.class,userName);
        }
    }
}
