package pnsu.gob.pe.sbprocesarhoras.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pnsu.gob.pe.sbprocesarhoras.dto.CompensacionHorasDto;
import pnsu.gob.pe.sbprocesarhoras.model.TCompensacionHoras;
import pnsu.gob.pe.sbprocesarhoras.model.TMarcacionPersonal;
import pnsu.gob.pe.sbprocesarhoras.model.TPersonal;
import pnsu.gob.pe.sbprocesarhoras.repo.ICompHorasRepo;
import pnsu.gob.pe.sbprocesarhoras.repo.IGenericRepo;
import pnsu.gob.pe.sbprocesarhoras.repo.IMarcacionPersonalRepo;
import pnsu.gob.pe.sbprocesarhoras.service.ICompHorasService;
import pnsu.gob.pe.sbprocesarhoras.util.EAIUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompHorasServiceImpl extends GenericServiceImpl<TCompensacionHoras,Integer> implements ICompHorasService {

    private final ICompHorasRepo repo;

    private final EAIUtil util;

    @Override
    protected IGenericRepo<TCompensacionHoras, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<TCompensacionHoras> listAllNull(){

        List<TCompensacionHoras> list= repo.listAllNull();

        return list;

    }

    public int updateTComp(Integer idcomphoras,Integer idtestado){
        return repo.updateEstadoCompHoras(idcomphoras,idtestado);
    }

}
