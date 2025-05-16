package org.jalcantararivera.mitosales.service.impl;

import org.jalcantararivera.mitosales.exception.ModelNotFoundException;
import org.jalcantararivera.mitosales.repo.IGenericRepo;
import org.jalcantararivera.mitosales.service.IGenericService;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public abstract class CRUDImpl<T,ID> implements IGenericService<T,ID> {

    protected abstract IGenericRepo<T,ID> getRepo();
    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(T t, ID id) throws Exception {
        //JAVA REFLECTION
        Class<?> clazz= t.getClass();
        String className= clazz.getSimpleName();
        String methodName="setId"+className;

        Method setIdMethod= clazz.getMethod(methodName,id.getClass());
        setIdMethod.invoke(t,id);

        getRepo().findById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id));
        return getRepo().save(t);
    }

    @Override
    public List<T> readAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T readById(ID id) throws Exception {
        return getRepo().findById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id));
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepo().findById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id));
        getRepo().deleteById(id);
    }


}
