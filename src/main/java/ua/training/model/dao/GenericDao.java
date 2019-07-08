package ua.training.model.dao;

import java.util.List;

public interface GenericDao<T> extends AutoCloseable{

    void create(T entity);
    T findById(int id);
    List<T> findAll();
    void update(T t);
    boolean delete(int id);
    void close();

}
