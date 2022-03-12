package repositories;

import java.util.List;

public interface GenericRepository<T,ID> {
    T save(T t);
    T update(T t);
    void delete(T t);
    /*void deleteById(ID id);*/
    T findById(ID id);
    List<T> findAll();
}
