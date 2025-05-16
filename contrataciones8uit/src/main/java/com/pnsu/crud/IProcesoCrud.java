package com.pnsu.crud;

import com.pnsu.crud.Impl.CRUDImpl;
import com.pnsu.model.TProceso;
import com.pnsu.repo.IProcesoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface IProcesoCrud extends ICRUDService<TProceso,Integer>{

}
