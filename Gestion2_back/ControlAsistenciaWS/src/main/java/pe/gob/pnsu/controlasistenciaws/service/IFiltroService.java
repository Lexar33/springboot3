package pe.gob.pnsu.controlasistenciaws.service;

import pe.gob.pnsu.controlasistenciaws.dto.MarcacionPersonalDto;
import pe.gob.pnsu.controlasistenciaws.model.TControlAsistParcial;

import java.time.LocalDate;
import java.util.List;

public interface IFiltroService {

    List<MarcacionPersonalDto> filtrarListaMarcacion(List<MarcacionPersonalDto> listMarcacionPersonal, LocalDate diaAnalizado, String documentoidentidad);
    List<TControlAsistParcial> filtrarControlAsistenciaParcial(List<TControlAsistParcial> listControlAsistenciaParcial, LocalDate diaAnalizado, Integer idpersonal);

}
