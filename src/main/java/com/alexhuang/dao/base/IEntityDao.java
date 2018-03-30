package com.alexhuang.dao.base;

import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface IEntityDao<T> {

    T load(String id);
    
    List<T> findBy(DetachedCriteria criteria);
    
    T findAny(DetachedCriteria criteria);
    
    void save(T entity);

    void saveBatch(List<T> entityList);

    void update(T entity);

    void updateBatch(List<T> entityList);

    void saveOrUpdate(T entity);

    void saveOrUpdateBatch(List<T> entityList);

    void remove(T entity);

    void removeBatch(Collection<T> entityList);
    
    List<T> findByBean(T bean);

    List<T> findAll();

    void flush();

    List<T> find(String propertyName, Object value);

    T findAny(String propertyName, Object value);
}
