package pnsu.gob.pe.sbprocesarhoras.service;

import java.util.List;

public interface IGenericService <T,ID>{

    T save(T t) throws Exception;
    T update(T t, ID id ) throws Exception;
    List<T> readAll() throws Exception;
    T readById(ID id ) throws Exception;
    void delete(ID id) throws Exception;

}
