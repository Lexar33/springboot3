package pnsu.gob.pe.sbprocesarhoras.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pnsu.gob.pe.sbprocesarhoras.model.TMarcacionPersonal;
import pnsu.gob.pe.sbprocesarhoras.repo.IGenericRepo;
import pnsu.gob.pe.sbprocesarhoras.repo.IMarcacionPersonalRepo;
import pnsu.gob.pe.sbprocesarhoras.service.IMarcPersonalService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarcPersonalServiceImpl extends GenericServiceImpl<TMarcacionPersonal, Integer> implements IMarcPersonalService {

    private final IMarcacionPersonalRepo repo;

    @Override
    protected IGenericRepo<TMarcacionPersonal, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<TMarcacionPersonal> listbyIdPersonalFecha(Integer idPersonal, LocalDate fecha) {
        return repo.findByIdpersonalAndFecha(idPersonal,fecha);
    }

}
