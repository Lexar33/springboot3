package pe.gob.pnsu.controlasistenciaws.service;

import pe.gob.pnsu.controlasistenciaws.dto.DocumentoDto;
import pe.gob.pnsu.controlasistenciaws.dto.MarcacionPersonalDto;
import pe.gob.pnsu.controlasistenciaws.dto.VacacionDto;
import pe.gob.pnsu.controlasistenciaws.dto.VwPersonalDto;
import pe.gob.pnsu.controlasistenciaws.model.TControlAsistParcial;
import pe.gob.pnsu.controlasistenciaws.model.TFeriado;
import pe.gob.pnsu.controlasistenciaws.model.TPersonal;

import java.time.LocalDate;
import java.util.List;


public interface IPersonalService extends IGenericService<TPersonal,Integer> {


    List<Object[]> listarPersonal(Integer idpersona);

    //List<VwPersonalDto> listarPersonalFechaHasta(String documentoidentidad, String fechahasta);
    List<VwPersonalDto> listarPersonalFechaHasta();

    List<MarcacionPersonalDto> listarMarcacionPersonal(LocalDate lddesde, LocalDate ldhasta);

    List<TFeriado> listFeriado(LocalDate lddesde, LocalDate ldhasta);

    List<DocumentoDto> listarDocumentoReporte(LocalDate lddesde, LocalDate ldhasta);
    List<VacacionDto> listarVacacionPersonal(LocalDate lddesde, LocalDate ldhasta);




}

