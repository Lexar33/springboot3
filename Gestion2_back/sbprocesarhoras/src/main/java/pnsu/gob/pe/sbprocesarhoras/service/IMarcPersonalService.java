package pnsu.gob.pe.sbprocesarhoras.service;

import pnsu.gob.pe.sbprocesarhoras.model.TCompensacionHoras;
import pnsu.gob.pe.sbprocesarhoras.model.TMarcacionPersonal;

import java.time.LocalDate;
import java.util.List;

public interface IMarcPersonalService extends IGenericService<TMarcacionPersonal,Integer> {

    public List<TMarcacionPersonal> listbyIdPersonalFecha(Integer idPersonal, LocalDate fecha);
}
