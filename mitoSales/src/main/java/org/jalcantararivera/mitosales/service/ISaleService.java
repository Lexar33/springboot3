package org.jalcantararivera.mitosales.service;

import org.jalcantararivera.mitosales.dto.IProcedureDTO;
import org.jalcantararivera.mitosales.dto.ProcedureDTO;
import org.jalcantararivera.mitosales.model.Sale;

import java.util.List;

public interface ISaleService extends IGenericService<Sale,Integer>{
    List<ProcedureDTO> callProcedure();
    List<IProcedureDTO> callProcedure2();
    List<ProcedureDTO> callProcedure3();

}
