package com.alexhuang.dao.base.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.alexhuang.dao.base.BaseDao;
import com.alexhuang.dao.base.IEntityDao;

@Transactional
public class BaseEntityDao<T> extends BaseDao implements IEntityDao<T> {

    protected Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public BaseEntityDao() {
        entityClass = (Class<T>) getInitEntityClass();
    }

    protected Class<?> getInitEntityClass() {
        Class<?> cls = getArgTypesFrom(getClass());
        if (cls == null) {
            cls = getFirstArgTypes(getClass());
        }
        return cls;
    }

    private Class<?> getFirstArgTypes(Class<?> beanClass) {
        Type type = beanClass.getGenericSuperclass();
        if (type != null) {
            if (ParameterizedType.class.isInstance(type)) { // NOPMD
                ParameterizedType pType = (ParameterizedType) type;
                if (pType.getActualTypeArguments().length >= 1) {
                    Type argType1 = pType.getActualTypeArguments()[0];
                    if ((argType1 instanceof Class)) {
                        return (Class<?>) argType1;
                    }
                }
                return null;
            }
        }
        return getFirstArgTypes(beanClass.getSuperclass());
    }

    protected Class<?> getArgTypesFrom(Class<?> beanClass) {
        if (BaseEntityDao.class.isAssignableFrom(beanClass)) {
            Type type = beanClass.getGenericSuperclass();
            if (type != null) {
                if (ParameterizedType.class.isInstance(type)) {
                    ParameterizedType pType = (ParameterizedType) type;
                    if (pType.getRawType().equals(BaseEntityDao.class) && pType.getActualTypeArguments().length >= 1) {
                        Type argType1 = pType.getActualTypeArguments()[0];
                        if ((argType1 instanceof Class)) {
                            return (Class<?>) argType1;
                        }
                        return null;
                    }
                }
            }
            return getArgTypesFrom(beanClass.getSuperclass());
        }
        return null;
    }
    
    public int countBy(DetachedCriteria criteria) {
        Criteria criteriaExe = criteria.getExecutableCriteria(currentSession());
        return getRowCount(criteriaExe);
    }

    private int getRowCount(Criteria criteria) {
        Number countTmp = (Number) criteria.setProjection(Projections.rowCount()).uniqueResult();
        if (null == countTmp) {
            throw new HibernateException(
                "The entity class of current criteria does not mapping or has mapping mistake.");
        }
        return countTmp.intValue();
    }
    
    @Override
    public void save(T entity) {
        super.currentSession().save(entity);
    }

    @Override
    public void saveBatch(List<T> entityList) {
        for (T e : entityList) {
            this.save(e);
        }
    }

    @Override
    public void update(T entity) {
        super.currentSession().update(entity);
    }

    @Override
    public void updateBatch(List<T> entityList) {
        for (T o : entityList) {
            this.update(o);
        }
    }

    @Override
    public void saveOrUpdate(T entity) {
        super.currentSession().saveOrUpdate(entity);
    }

    @Override
    public void saveOrUpdateBatch(List<T> entityList) {
        for (T o : entityList) {
            this.saveOrUpdate(o);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findBy(DetachedCriteria criteria) {
        return (List<T>) criteria.getExecutableCriteria(super.currentSession()).list();
    }

    @Override
    public T findAny(DetachedCriteria criteria) {
        List<T> res = this.findBy(criteria);
        if (res.isEmpty())
            return null;
        return res.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByBean(T bean) {
        return (List<T>) super.currentSession().createCriteria(entityClass).add(Example.create(bean)).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() {
        return super.currentSession().createCriteria(entityClass).list();
    }

    @Override
    public void remove(T entity) {
        super.currentSession().delete(entity);
    }

    @Override
    public void removeBatch(Collection<T> entityList) {
        for (T e : entityList) {
            this.remove(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T load(String id) {
    	if (id == null) return null;
        return (T) super.currentSession().get(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> find(String propertyName, Object value) {
        return super.currentSession().createCriteria(entityClass).add(Restrictions.eq(propertyName, value)).list();
    }

    @Override
    public T findAny(String propertyName, Object value) {
        List<T> list = this.find(propertyName, value);
        if (!list.isEmpty())
            return list.get(0);
        return null;
    }
}
