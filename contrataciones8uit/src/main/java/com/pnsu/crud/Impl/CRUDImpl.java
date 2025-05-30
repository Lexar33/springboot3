package com.pnsu.crud.Impl;

import com.pnsu.crud.ICRUDService;
import com.pnsu.exception.ModelNotFoundException;
import com.pnsu.repo.IGenericRepo;

import java.util.List;

public abstract class CRUDImpl<T,ID> implements ICRUDService<T,ID> {
     protected abstract IGenericRepo<T,ID> getRepo();


    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(T t, ID id) throws Exception {
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
