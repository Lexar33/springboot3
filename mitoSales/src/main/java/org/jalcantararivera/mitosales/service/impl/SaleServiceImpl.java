package org.jalcantararivera.mitosales.service.impl;

import lombok.RequiredArgsConstructor;
import org.jalcantararivera.mitosales.dto.IProcedureDTO;
import org.jalcantararivera.mitosales.dto.ProcedureDTO;
import org.jalcantararivera.mitosales.model.Sale;
import org.jalcantararivera.mitosales.repo.IGenericRepo;
import org.jalcantararivera.mitosales.repo.ISaleRepo;
import org.jalcantararivera.mitosales.service.ISaleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl extends CRUDImpl<Sale,Integer> implements ISaleService {

    private final ISaleRepo repo;
    @Override
    protected IGenericRepo<Sale, Integer> getRepo() {
        return repo;
    }


    @Override
    public List<ProcedureDTO> callProcedure() {
        List<ProcedureDTO> list= new ArrayList<>();
        repo.callProcedure1().forEach(e-> {
            ProcedureDTO dto= new ProcedureDTO();
            dto.setQuantity((Long) e[0]);
            dto.setDatetime((String) e[1]);
            list.add(dto);
        });
        return list;
    }

    @Override
    public List<IProcedureDTO> callProcedure2() {
        return repo.callProcedure2();
    }

    @Override
    public List<ProcedureDTO> callProcedure3() {
        return repo.callProcedure3();
    }
}

