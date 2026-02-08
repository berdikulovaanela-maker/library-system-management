package edu.aitu.oop3.db.CoreComponent.Repository.Interface;

import java.util.List;

public interface CrudRepository <T,ID> {
    T findById(ID id);
    List<T> findAll();
    void save(T entity);
    void delete(ID id);
}
