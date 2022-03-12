package repositories;

import models.Course;
import models.Student;
import models.Term;

import java.util.List;

public class TermRepository extends GenericRepositoryImplementation<Term , Long> implements GenericRepository<Term,Long>{

    @Override
    public Term findById(Long id) {
        try(var session = sessionFactory.openSession()){
            return session.find(Term.class, id);
        }
    }

    @Override
    public List<Term> findAll() {
        try(var session = sessionFactory.openSession()){
            return session
                    .createQuery("SELECT t FROM models.Term t" , Term.class)
                    .list();
        }
    }

    public void truncate(){
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.createNativeQuery("TRUNCATE Term CASCADE ", Term.class)
                    .executeUpdate();
            transaction.commit();
        }
    }

    public void deleteById(Long id) {
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try{
                var term = session.createQuery("FROM models.Term t WHERE t.id = :id ")
                        .setParameter("id",id)
                        .getSingleResult();
                session.delete(term);
                transaction.commit();
            }catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }
    }
}
