package com.springmongo.Base;

import com.springmongo.Base.Dao.BaseDao;
import com.springmongo.Base.Dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Mohammad on 7/18/2015.
 */
@Service
public class BaseService<T extends  BaseModel<Long>> {
    private Class<T> clazz;
    @Autowired
    private GenericDao<T> dao;

    public BaseService() {
        if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
            ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
            if (genericSuperclass != null
                    && genericSuperclass.getActualTypeArguments() != null
                    && genericSuperclass.getActualTypeArguments().length > 0) {
                if (genericSuperclass.getActualTypeArguments()[0] instanceof Class) {
                    clazz = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
                }
            }
        }
    }

    public Boolean save(T entity) {
        try {
            dao.save(entity);
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }

    public Boolean delete(T entity) {
        try {
            dao.delete(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<T> getAll() {
        dao.setTclass(clazz);
        return  dao.getAll();
    }

    public List<T> find(String[] properties , Object[] values){
        try {
            dao.setTclass(clazz);
            return dao.find(properties , values);
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
}
