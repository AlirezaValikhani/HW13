package repositories;

import models.Term;
import org.hibernate.SessionFactory;

import java.util.List;

public class GenericRepositoryImplementation<T,ID> implements GenericRepository<T,ID>{
    protected SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public T save(T t) {
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try{
                session.save(t);
                transaction.commit();
                return t;
            }catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public T update(T t) {
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try{
                session.update(t);
                transaction.commit();
                return t;
            }catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void delete(T t) {
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try{
                session.delete(t);
                transaction.commit();
            }catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public T findById(ID id) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }
}
