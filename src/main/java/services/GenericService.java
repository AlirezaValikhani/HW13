package services;

import java.util.List;

public interface GenericService<T,ID> {
    T save(T t);
    void update(T t);
    void delete(T t);
    void deleteById(ID id);
    T findById(ID id);
    List<T> findAll();
}
