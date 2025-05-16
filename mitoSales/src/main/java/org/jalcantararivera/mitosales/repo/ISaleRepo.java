package org.jalcantararivera.mitosales.repo;

import org.jalcantararivera.mitosales.dto.IProcedureDTO;
import org.jalcantararivera.mitosales.dto.ProcedureDTO;
import org.jalcantararivera.mitosales.dto.SaleDTO;
import org.jalcantararivera.mitosales.model.Sale;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISaleRepo extends IGenericRepo<Sale,Integer> {
    @Query(value="call fn_sales()",nativeQuery = true)
    List<Object[]> callProcedure1();

    @Query(value="call fn_sales()",nativeQuery = true)
    List<IProcedureDTO> callProcedure2();

    @Query(name="Sale.fn_sales",nativeQuery = true)
    List<ProcedureDTO> callProcedure3();
}
