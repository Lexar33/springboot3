package pe.gob.pnsu.controlasistenciaws.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.gob.pnsu.controlasistenciaws.dto.MarcacionPersonalDto;
import pe.gob.pnsu.controlasistenciaws.model.TControlAsistParcial;
import pe.gob.pnsu.controlasistenciaws.service.IFiltroService;
import pe.gob.pnsu.controlasistenciaws.util.EAIUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FiltroServiceImpl implements IFiltroService {

    private final EAIUtil util;

    @Override
    public List<MarcacionPersonalDto> filtrarListaMarcacion(List<MarcacionPersonalDto> listMarcacionPersonal, LocalDate diaAnalizado, String documentoidentidad) {
        //List<MarcacionPersonalDto> listMarcacion = new List<MarcacionPersonalDto>();
        List<MarcacionPersonalDto> listMarcacionPersonalFiltrado = listMarcacionPersonal.stream()
                .filter((MarcacionPersonalDto t) -> t.getFecha().isEqual(diaAnalizado) && t.getDocumentoidentidad().equals(documentoidentidad))
                .toList();

        //List<MarcacionPersonalDto> lista = new ArrayList<MarcacionPersonalDto>(listMarcacionPersonal);
        //lista.removeAll(listMarcacionPersonalFiltrado);


        if (listMarcacionPersonalFiltrado.size()>=2)
        {
            MarcacionPersonalDto firstMarca = listMarcacionPersonalFiltrado.get(0);
            log.warn("Cuidado,existe Marcacion Personal repetido"+" "+ firstMarca.getFecha().toString()+" "+firstMarca.getNombres()+" "+firstMarca.getDocumentoidentidad());
        }


        //listMarcacionPersonal.forEach(t->log.info(t.getSfecha().toString()+" -- "+t.getDocumentoidentidad()+"##"+diaAnalizado.toString()+"##"+documentoidentidad));
        return listMarcacionPersonalFiltrado;
    }

    @Override
    public List<TControlAsistParcial> filtrarControlAsistenciaParcial(List<TControlAsistParcial> listControlAsistenciaParcial, LocalDate diaAnalizado, Integer idpersonal) {
        List<TControlAsistParcial> listControlAsistenciaParcialFiltrado = listControlAsistenciaParcial.stream()
                .filter((TControlAsistParcial t) -> util.convertToLocalDateViaMilisecond(t.getFecha()).isEqual(diaAnalizado) && t.getIdpersonal().getIdpersonal().equals(idpersonal))
                .toList();

        //log.info("Tamaño de lista control asist. parcial filtrado:"+String.valueOf(listControlAsistenciaParcialFiltrado.size())+"-");
        if (listControlAsistenciaParcialFiltrado.size()>=2)
        {
           // TControlAsistParcial firstControl = listControlAsistenciaParcialFiltrado.get(0);
           // log.warn("Cuidado,existe asistencia parcial repetida:"+ listControlAsistenciaParcialFiltrado.size()+" "+firstControl.getFecha()+" "+firstControl.getDocumentoidentidad());
        }
        return listControlAsistenciaParcial;
    }


}
