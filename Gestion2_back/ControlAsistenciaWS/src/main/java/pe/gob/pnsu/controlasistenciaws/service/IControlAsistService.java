package pe.gob.pnsu.controlasistenciaws.service;

import pe.gob.pnsu.controlasistenciaws.model.TControlAsistParcial;

import java.time.LocalDate;
import java.util.List;

public interface IControlAsistService extends IGenericService<TControlAsistParcial,Integer> {

    List<TControlAsistParcial> listarAsistenciaParcial (LocalDate desde, LocalDate hasta);


}
