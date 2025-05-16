package pnsu.gob.pe.sbprocesarhoras.service;

import pnsu.gob.pe.sbprocesarhoras.dto.CompensacionHorasDto;
import pnsu.gob.pe.sbprocesarhoras.model.TCompensacionHoras;

import java.util.List;

public interface ICompHorasService extends IGenericService<TCompensacionHoras,Integer> {

  //  public List<TCompensacionHoras> listCompHoras();
    //public List<CompensacionHorasDto> prueba();
    public List<TCompensacionHoras> listAllNull();

    public int updateTComp(Integer idcomphoras,Integer idtestado);

}
