package ru.ivmiit.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T> {
    T find(Long id);
    void save(T model);
    void update(T model);
    void add(T model);
    void delete(T model);
    T find(T model);
    List<T> findAll();
}
