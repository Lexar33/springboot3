package com.pnsu.crud.Impl;

import com.pnsu.crud.IProcesoCrud;
import com.pnsu.model.TProceso;
import com.pnsu.repo.IGenericRepo;
import com.pnsu.repo.IProcesoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcesoCrud  extends CRUDImpl<TProceso,Integer> implements IProcesoCrud {

    private final IProcesoRepo repo;

    @Override
    protected IGenericRepo<TProceso, Integer> getRepo() {
        return repo;
    }
}
