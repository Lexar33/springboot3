package pnsu.gob.pe.sbprocesarhoras.service.impl;

import pnsu.gob.pe.sbprocesarhoras.exception.ModelNotFoundException;
import pnsu.gob.pe.sbprocesarhoras.repo.IGenericRepo;
import pnsu.gob.pe.sbprocesarhoras.service.IGenericService;

import java.util.List;

public abstract class GenericServiceImpl<T,ID> implements IGenericService<T,ID> {

    protected abstract IGenericRepo<T,ID> getRepo();
    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(T t, ID id) throws Exception {

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
