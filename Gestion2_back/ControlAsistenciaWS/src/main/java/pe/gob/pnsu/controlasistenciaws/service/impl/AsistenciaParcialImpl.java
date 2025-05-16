package pe.gob.pnsu.controlasistenciaws.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import pe.gob.pnsu.controlasistenciaws.dto.DocumentoDto;
import pe.gob.pnsu.controlasistenciaws.dto.MarcacionPersonalDto;
import pe.gob.pnsu.controlasistenciaws.dto.VacacionDto;
import pe.gob.pnsu.controlasistenciaws.dto.VwPersonalDto;
import pe.gob.pnsu.controlasistenciaws.model.Auxiliar;
import pe.gob.pnsu.controlasistenciaws.model.TControlAsistParcial;
import pe.gob.pnsu.controlasistenciaws.model.TFeriado;
import pe.gob.pnsu.controlasistenciaws.service.IAsistenciaParcial;
import pe.gob.pnsu.controlasistenciaws.service.IControlAsistService;
import pe.gob.pnsu.controlasistenciaws.service.IFiltroService;
import pe.gob.pnsu.controlasistenciaws.service.IPersonalService;
import pe.gob.pnsu.controlasistenciaws.util.EAIUtil;
import pe.gob.pnsu.controlasistenciaws.util.RestResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsistenciaParcialImpl implements IAsistenciaParcial {


    private final IPersonalService oPersonalService;

    private final IControlAsistService oAsistenciaService;

    private final IFiltroService oFiltroService;

    private final EAIUtil util;


    /*
        @Autowired
        PersonalWs oPersonalWS;
    */
    @Override
    public RestResponse registrarcontrolasistenciaparcial(String desde, String hasta) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //formatter.withLocale():
        LocalDate lddesde = LocalDate.parse(desde, formatter);
        LocalDate ldhasta = LocalDate.parse(hasta, formatter);

        //DIFERENCIA DE DIAS
        long numOfDaysBetween = ChronoUnit.DAYS.between(lddesde, ldhasta) + 1;
        log.info("Cantidad de Dias {}", numOfDaysBetween);

        log.info("===================================================");


        //LISTA TODOS LO USUARIOS A LA FECHA
        List<VwPersonalDto> listPersonal = oPersonalService.listarPersonalFechaHasta();

        //LISTA T_CONTROL_ASIST_PARCIAL
        List<TControlAsistParcial> listControlAsistenciaParcial = oAsistenciaService.listarAsistenciaParcial(lddesde, ldhasta);

        //LISTA MARCACION PERSONAL
        List<MarcacionPersonalDto> listMarcacionPersonal = oPersonalService.listarMarcacionPersonal(lddesde, ldhasta);

        //LISTA FERIADOS
        List<TFeriado> listFeriado = oPersonalService.listFeriado(lddesde, ldhasta);

        //LISTAR DOCUMENTOS REGISTRADOS
        List<DocumentoDto> listDocumentos = oPersonalService.listarDocumentoReporte(lddesde, ldhasta);

        //PERSONAL DE VACACIONES
        List<VacacionDto> listVacaciones = oPersonalService.listarVacacionPersonal(lddesde, ldhasta);

        IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj((IntFunction<?>) i -> {

                    listPersonal.forEach((VwPersonalDto temp) -> {


                        //Date.from(lddesde.plusDays(i).atStartOfDay(ZoneId.systemDefault()).toInstant();
                        LocalDate diaAnalizado = LocalDate.from(lddesde.plusDays(i).atStartOfDay(ZoneId.systemDefault()));
                        String documentoIdentidad = temp.getDocumentoidentidad();
                        Integer idpersonal = temp.getIdpersonal();

                        ////////////////////////////////////////////////////////
                        LocalDate fechaInicio = LocalDate.parse(temp.getFechainicio(), formatter);

                        LocalDate fechaCese = temp.getDfechacesecontrato();
                        Boolean cesePresent = Optional.ofNullable(fechaCese).isPresent();

                        LocalDate fechaBajaAdenda = temp.getDfechabajaadenda();
                        Boolean bajaPresent = Optional.ofNullable(fechaBajaAdenda).isPresent();

                        LocalDate fechaFinAdenda = temp.getDfechafinadenda();
                        Boolean finAdendaPresent = Optional.ofNullable(fechaFinAdenda).isPresent();

                        LocalDate fechaFinContrato = temp.getDfechafincontrato();
                        Boolean finContratoPresent = Optional.ofNullable(fechaFinContrato).isPresent();
                        ////////////////////////////////////////////////////////
                        try {


                            if (fechaInicio.isBefore(diaAnalizado)
                                    ? (cesePresent
                                    ? fechaCese.isAfter(diaAnalizado) || fechaCese.isEqual(diaAnalizado)
                                    : (bajaPresent)
                                    ? fechaBajaAdenda.isAfter(diaAnalizado) || fechaBajaAdenda.isEqual(diaAnalizado)
                                    : (finAdendaPresent)
                                    ? fechaFinAdenda.isAfter(diaAnalizado) || fechaFinAdenda.isEqual(diaAnalizado)
                                    : (finContratoPresent)
                                    ? fechaFinContrato.isAfter((diaAnalizado)) || fechaFinContrato.isEqual(diaAnalizado)
                                    : true)
                                    : fechaInicio.isEqual(diaAnalizado))
                            {

                                //FILTRA MARCACION DE PERSONAL POR DNI DE LA PERSONA Y DIA
                                List<MarcacionPersonalDto> listMarcacionPersonalFiltrado = oFiltroService.filtrarListaMarcacion(listMarcacionPersonal, diaAnalizado, documentoIdentidad);
                                listMarcacionPersonal.removeAll(listMarcacionPersonalFiltrado);
                                //FILTRA ASISTENCIA PARCIAL POR IDPERSONAL Y DIA
                                List<TControlAsistParcial> listControlAsistenciaParcialFiltrado = oFiltroService.filtrarControlAsistenciaParcial(listControlAsistenciaParcial, diaAnalizado, idpersonal);
                                listControlAsistenciaParcial.removeAll(listControlAsistenciaParcialFiltrado);

                                JSONArray arrayDocumentosJSON = new JSONArray();

                                Auxiliar aux = new Auxiliar();


                                //LISTA DE DOCUMENTOS QUE TIENE RANGO DE FECHAS
                                List<DocumentoDto> listDocumentosFiltradoBetween = listDocumentos.stream()
                                        .filter((DocumentoDto tdoc)
                                                -> tdoc.getDocumentoidentidad().equals(temp.getDocumentoidentidad()) //Object.equals(tdoc.getDocumentoidentidad(),temp.getDocumentoidentidad())
                                                && isWithinRange(diaAnalizado, util.convertToLocalDateViaMilisecond(tdoc.getFechainicio()), util.convertToLocalDateViaMilisecond(tdoc.getFechafin()))
                                                && !tdoc.getIdtestadodocumento().equals(126)  //Object.equals(tdoc.getIdtestadodocumento(),126)
                                        ).toList();



                                for (DocumentoDto documentoDto : listDocumentosFiltradoBetween) {

                                    //////////////////////////////////////////////////////////
                                    LocalDate docFechaInicio = util.convertToLocalDateViaMilisecond(documentoDto.getFechainicio());
                                    LocalDate docFechaFin = util.convertToLocalDateViaMilisecond(documentoDto.getFechafin());
                                    LocalDate docFechaOcurrencia = util.convertToLocalDateViaMilisecond(documentoDto.getFechaocurrencia());

                                    JSONObject documentoJSON = new JSONObject();
                                    documentoJSON.put("iddocumento", documentoDto.getIddocumento().toString());
                                    documentoJSON.put("idtasunto", documentoDto.getIdtasunto());
                                    documentoJSON.put("idtestadodocumento", documentoDto.getIdtestadodocumento());
                                    documentoJSON.put("tipodocumento", documentoDto.getTipodocumento());
                                    documentoJSON.put("estadodocumento", documentoDto.getEstadodocumento());
                                    documentoJSON.put("tiposolicitud", documentoDto.getTiposolicitud().replace("SOLICITUD ", ""));
                                    documentoJSON.put("asunto", documentoDto.getAsunto());
                                    documentoJSON.put("horainicio", documentoDto.getHorasalida());
                                    documentoJSON.put("horafin", documentoDto.getHoraretorno());
                                    documentoJSON.put("horas", Optional.ofNullable(documentoDto.getHoras()).isPresent()
                                            ? documentoDto.getHoras()
                                            : Optional.ofNullable(documentoDto.getHoraretorno()).isPresent() && Optional.ofNullable(documentoDto.getHorasalida()).isPresent()
                                            ? (int) ((documentoDto.getHoraretorno().getTime() - documentoDto.getHorasalida().getTime()) / (60 * 1000))
                                            : aux.getHorasatrabajar());

                                    documentoJSON.put("minutos", Optional.ofNullable(documentoDto.getHoras()).isPresent()
                                            ? documentoDto.getHoras() * 60
                                            : Optional.ofNullable(documentoDto.getHoraretorno()).isPresent() && Optional.ofNullable(documentoDto.getHorasalida()).isPresent()
                                            ? (int) ((documentoDto.getHoraretorno().getTime() - documentoDto.getHorasalida().getTime()) / (60 * 1000)) * 60
                                            : aux.getHorasatrabajar() * 60);

                                    documentoJSON.put("fechainicio", Optional.ofNullable(docFechaInicio).isPresent() ? docFechaInicio : null);
                                    documentoJSON.put("fechafin", Optional.ofNullable(docFechaFin).isPresent() ? docFechaFin : null);
                                    documentoJSON.put("fechaocurrencia", Optional.ofNullable(docFechaOcurrencia).isPresent() ? docFechaOcurrencia : null);
                                    arrayDocumentosJSON.put(documentoJSON);
                                    aux.setDiasolicitado(true);
                                    //////////////////////////////////////////////////////////


                                    if (documentoDto.getIdtestadodocumento().equals(124)) {
                                        if (null != documentoDto.getIdtasunto()) {
                                            //SI ES SABADO O DOMINGO
                                            if (diaAnalizado.getDayOfWeek().getValue() == 6 || diaAnalizado.plusDays(i).getDayOfWeek().getValue() == 7) {
                                                if (documentoDto.getIdtasunto() == 103 || documentoDto.getIdtasunto() == 104) {
                                                    aux.setDiaregularizado(true);
                                                    aux.setHorastrabajadas(aux.getHorastrabajadas() >= 15 ? aux.getHorastrabajadas() : aux.getHorastrabajadas() + aux.getHorasatrabajar());
                                                    aux.setHorasatrabajar(aux.getHorascomisiondeservicio() >= 15 ? aux.getHorascomisiondeservicio() : aux.getHorascomisiondeservicio() + aux.getHorasatrabajar());
                                                }
                                            } else {
                                                // SI EL ASUNTO ES DESCANSO MEDICO O PERMISO SALUD SUBSIDIO
                                                if (documentoDto.getIdtasunto() == 106 || documentoDto.getIdtasunto() == 131) {
                                                    aux.setDiaregularizado(true);
                                                    aux.setHorastrabajadas(aux.getHorastrabajadas() >= aux.getHorasatrabajar() ? aux.getHorastrabajadas() : aux.getHorastrabajadas() + aux.getHorasatrabajar());
                                                    aux.setMinutossalud(aux.getMinutossalud() >= (15 * 60) ? aux.getMinutossalud() : aux.getMinutossalud() + (aux.getHorasatrabajar() * 60));
                                                }


                                                //////////////////////////////////////////////////////////
                                                //////////////////////////////////////////////////////////
                                                //////////////////////////////////////////////////////////
                                                //////////////////////////////////////////////////////////
                                                //////////////////////////////////////////////////////////
                                                //////////////////////////////////////////////////////////

                                            }
                                        }
                                    }
                                }



                                //DOCUMENTOS FILTRADOS POR DIA ANALIZADO
                                List<DocumentoDto> listDocumentosFiltrado = listDocumentos.stream()
                                        .filter((DocumentoDto tdoc)
                                                ->
                                                tdoc.getDocumentoidentidad().equals(temp.getDocumentoidentidad())
                                                && tdoc.getFechaocurrencia() !=null
                                                && util.convertToLocalDateViaMilisecond(tdoc.getFechaocurrencia()).equals(diaAnalizado)
                                                && tdoc.getIdtestadodocumento().equals(126)
                                        ).toList();
                                listDocumentos.removeAll(listDocumentosFiltrado);

                                try {

                                    for (DocumentoDto documentoDto : listDocumentosFiltrado) {

                                        //////////////////////////////////////////////////////////
                                        LocalDate docFechaInicio = util.convertToLocalDateViaMilisecond(documentoDto.getFechainicio());
                                        LocalDate docFechaFin = util.convertToLocalDateViaMilisecond(documentoDto.getFechafin());
                                        LocalDate docFechaOcurrencia = util.convertToLocalDateViaMilisecond(documentoDto.getFechaocurrencia());

                                        JSONObject documentoJSON = new JSONObject();
                                        documentoJSON.put("iddocumento", documentoDto.getIddocumento().toString());
                                        documentoJSON.put("idtasunto", documentoDto.getIdtasunto());
                                        documentoJSON.put("idtestadodocumento", documentoDto.getIdtestadodocumento());
                                        documentoJSON.put("tipodocumento", documentoDto.getTipodocumento());
                                        documentoJSON.put("estadodocumento", documentoDto.getEstadodocumento());
                                        documentoJSON.put("tiposolicitud", documentoDto.getTiposolicitud().replace("SOLICITUD ", ""));
                                        documentoJSON.put("asunto", documentoDto.getAsunto());
                                        documentoJSON.put("horainicio", documentoDto.getHorasalida());
                                        documentoJSON.put("horafin", documentoDto.getHoraretorno());
                                        documentoJSON.put("horas", Optional.ofNullable(documentoDto.getHoras()).isPresent()
                                                ? documentoDto.getHoras()
                                                : Optional.ofNullable(documentoDto.getHoraretorno()).isPresent() && Optional.ofNullable(documentoDto.getHorasalida()).isPresent()
                                                ? (int) ((documentoDto.getHoraretorno().getTime() - documentoDto.getHorasalida().getTime()) / (60 * 1000))
                                                : aux.getHorasatrabajar());

                                        documentoJSON.put("minutos", Optional.ofNullable(documentoDto.getHoras()).isPresent()
                                                ? documentoDto.getHoras() * 60
                                                : Optional.ofNullable(documentoDto.getHoraretorno()).isPresent() && Optional.ofNullable(documentoDto.getHorasalida()).isPresent()
                                                ? (int) ((documentoDto.getHoraretorno().getTime() - documentoDto.getHorasalida().getTime()) / (60 * 1000)) * 60
                                                : aux.getHorasatrabajar() * 60);

                                        documentoJSON.put("fechainicio", Optional.ofNullable(docFechaInicio).isPresent() ? docFechaInicio : null);
                                        documentoJSON.put("fechafin", Optional.ofNullable(docFechaFin).isPresent() ? docFechaFin : null);
                                        documentoJSON.put("fechaocurrencia", Optional.ofNullable(docFechaOcurrencia).isPresent() ? docFechaOcurrencia : null);
                                        arrayDocumentosJSON.put(documentoJSON);
                                        aux.setDiasolicitado(true);

                                        //////////////////////////////////////////////////////////
                                        //////////////////////////////////////////////////////////
                                        //////////////////////////////////////////////////////////
                                        //////////////////////////////////////////////////////////
                                        //////////////////////////////////////////////////////////
                                        //////////////////////////////////////////////////////////

                                    }

                                    //LISTA LAS VACACIONES
                                    List<VacacionDto> listVacacionesFiltradoBetween = listVacaciones.stream()
                                            .filter((VacacionDto tvac)
                                                    -> Objects.equals(tvac.getDocumentoidentidad(), temp.getDocumentoidentidad()) && isWithinRange(diaAnalizado, util.convertToLocalDateViaMilisecond(tvac.getFechainicio()), util.convertToLocalDateViaMilisecond(tvac.getFechafin()))
                                            ).toList();

                                    for (VacacionDto vacacionDto : listVacacionesFiltradoBetween) {

                                        JSONObject documentoJSON = new JSONObject();
                                        documentoJSON.put("idvacacion", vacacionDto.getIdvacacion().toString());
                                        documentoJSON.put("idttipovacacion", vacacionDto.getIdttipovacacion());
                                        documentoJSON.put("idtestadovacacion", vacacionDto.getIdtestadovacacion());
                                        documentoJSON.put("tipovacacion", vacacionDto.getTipovacacion());
                                        documentoJSON.put("estadovacacion", vacacionDto.getEstadovacacion());
                                        documentoJSON.put("periodo", vacacionDto.getPeriodo());
                                        documentoJSON.put("diastomada", vacacionDto.getDiastomada());
                                        documentoJSON.put("horastomada", (vacacionDto.getDiastomada() * aux.getHorasatrabajar()));
                                        documentoJSON.put("fechainicio", Optional.ofNullable(vacacionDto.getFechainicio()).isPresent() ? util.convertToLocalDateViaMilisecond(vacacionDto.getFechainicio()) : null);
                                        documentoJSON.put("fechafin", Optional.ofNullable(vacacionDto.getFechafin()).isPresent() ? util.convertToLocalDateViaMilisecond(vacacionDto.getFechafin()) : null);
                                        documentoJSON.put("fechaocurrencia", Optional.ofNullable(vacacionDto.getFechaocurrencia()).isPresent() ? util.convertToLocalDateViaMilisecond(vacacionDto.getFechaocurrencia()) : null);
                                        arrayDocumentosJSON.put(documentoJSON);

                                        aux.setDiasolicitado(true);

                                        if (vacacionDto.getIdtestadovacacion().equals(145)) {

                                            if (null != vacacionDto.getIdttipovacacion()) {

                                                if (vacacionDto.getIdttipovacacion() == 116) {

                                                    String refrigerio = "13:00:00";
                                                    DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
                                                    Date dRefrigerio = dateFormat.parse(refrigerio);

                                                    if (vacacionDto.getHorasalida().getTime() <= temp.getHorarioingreso().getTime()) {
                                                        aux.setMinutoscapmediajornada1(aux.getMinutoscapmediajornada1() + (int) ((vacacionDto.getHoraretorno().getTime() - vacacionDto.getHorasalida().getTime()) / (60 * 1000)));
                                                    } else if (vacacionDto.getHorasalida().getTime() >= dRefrigerio.getTime()) {
                                                        aux.setMinutoscapmediajornada2(aux.getMinutoscapmediajornada2() + (int) ((vacacionDto.getHoraretorno().getTime() - vacacionDto.getHorasalida().getTime()) / (60 * 1000)));
                                                    } else {
                                                        aux.setMinutoscapmediajornada3(aux.getMinutoscapmediajornada3() + (int) ((vacacionDto.getHoraretorno().getTime() - vacacionDto.getHorasalida().getTime()) / (60 * 1000)));
                                                    }

                                                    aux.setHorastrabajadas(aux.getHorastrabajadas() >= 15 ? aux.getHorastrabajadas() : aux.getHorastrabajadas() + (aux.getHorasatrabajar() / 2));
                                                    aux.setHorasvacaciones(aux.getHorasvacaciones() >= aux.getHorasatrabajar() ? aux.getHorasvacaciones() : aux.getHorasvacaciones() + (aux.getHorasatrabajar() / 2));

                                                } else if (vacacionDto.getIdttipovacacion() == 99 || vacacionDto.getIdttipovacacion() == 100 || vacacionDto.getIdttipovacacion() == 98) {

                                                    aux.setHorastrabajadas(aux.getHorastrabajadas() >= aux.getHorasatrabajar() ? aux.getHorastrabajadas() : aux.getHorastrabajadas() + aux.getHorasatrabajar());
                                                    aux.setHorasvacaciones(aux.getHorasvacaciones() >= aux.getHorasatrabajar() ? aux.getHorasvacaciones() : aux.getHorasvacaciones() + aux.getHorasatrabajar());
                                                }

                                                aux.setDiaregularizado(true);
                                            }
                                        }
                                    }

                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }





                                if (!listMarcacionPersonalFiltrado.isEmpty()) {

                                    log.info("LIST MARCACION PERSONAL NO ES VACIO");














                                } else {

                                    log.info("::::LIST MARCACION ESTA VACIO -EXISTE LIST MARCACION PERSONAL::::");

                                    if (diaAnalizado.getDayOfWeek().getValue() == 6 || diaAnalizado.plusDays(i).getDayOfWeek().getValue() == 7 || aux.isDiaregularizado()) {

                                        List<TFeriado> listFeriadoFiltrado = listFeriado.stream()
                                                .filter((TFeriado tfer)
                                                        -> isWithinRange(diaAnalizado,util.convertToLocalDateViaMilisecond(tfer.getFechadesde()), util.convertToLocalDateViaMilisecond(tfer.getFechahasta()))
                                                ).toList();
                                        // SI EL DIA NO ES FERIADO
                                        if (listFeriadoFiltrado.isEmpty()) {
                                            Integer subtotal1
                                                    = aux.getMinutoscitacion1()
                                                    + aux.getMinutoscompensacion1()
                                                    + aux.getMinutoscapacitacionoficializada1()
                                                    + aux.getMinutospermisocongoceotros1()
                                                    + aux.getMinutoscitamedica1()
                                                    + aux.getMinutoslimametropolitana1()
                                                    + aux.getMinutoscapmediajornada1()
                                                    + aux.getMinutosasuntosparticulares1();

                                            Integer subtotal2
                                                    = aux.getMinutoscitacion2()
                                                    + aux.getMinutoscompensacion2()
                                                    + aux.getMinutoscapacitacionoficializada2()
                                                    + aux.getMinutospermisocongoceotros2()
                                                    + aux.getMinutoscitamedica2()
                                                    + aux.getMinutoslimametropolitana2()
                                                    + aux.getMinutoscapmediajornada2()
                                                    + aux.getMinutosasuntosparticulares2();

                                            Integer subtotal3
                                                    = aux.getMinutoscitacion3()
                                                    + aux.getMinutoscompensacion3()
                                                    + aux.getMinutoscapacitacionoficializada3()
                                                    + aux.getMinutospermisocongoceotros3()
                                                    + aux.getMinutoscitamedica3()
                                                    + aux.getMinutoslimametropolitana3()
                                                    + aux.getMinutoscapmediajornada3()
                                                    + aux.getMinutosasuntosparticulares3();

                                            Integer tardanza1 = 0;
                                            Integer tardanza2 = 0;
                                            Integer minutosfaltasalida1 = 0;
                                            Integer minutosfaltasalida2 = 0;
                                            Integer adicional1 = 0;
                                            Integer adicional2 = 0;



                                            if (!(Optional.ofNullable(temp.getIddependenciadesignado()).isPresent() || temp.getExoneradomarcar() != 1) || aux.isDiaregularizado()) {
                                                // SI NO TIENE ASISTENCIA PARCIAL REGISTRADA PARA ESE DIA
                                                if (listControlAsistenciaParcialFiltrado.isEmpty()) {

/*****************************************************************************************************************/
                                                    oReporteMarcacionDao.registrarControlAsistParcial(
                                                            new TControlAsistParcial(
                                                                    propiedades.VALUE_TIPO_ESTADO_CNTRL_ASISTENCIA_PENDIENTE,
                                                                    new Date(),
                                                                    Date.from(lddesde.plusDays(i).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                                                                    horastrabajadasremoto,
                                                                    horastrabajadaspresencial,
                                                                    horastrabajadas,
                                                                    0,
                                                                    horasatrabajar,
                                                                    (horasatrabajar - horastrabajadas) > 0 ? (horasatrabajar - horastrabajadas) : 0,
                                                                    minutospermisocongoce,
                                                                    minutospermisosingoce,
                                                                    horaslicenciacongoce,
                                                                    horaslicenciasingoce,
                                                                    horascomisiondeservicio,
                                                                    minutossalud,
                                                                    horasvacaciones,
                                                                    horascapacitacion,
                                                                    arrayDocumentosJSON.length() != 0 ? arrayDocumentosJSON.toString() : null,
                                                                    diaregularizado,
                                                                    diasolicitado,
                                                                    false,
                                                                    new Date(),
                                                                    Constantes.CODIGO_ESTADO_ACTIVO,
                                                                    new TPersonal(temp.getIdpersonal()),
                                                                    temp.getDocumentoidentidad()
                                                            ));
/*****************************************************************************************************************/
                                                } else {

/*****************************************************************************************************************/
                                                    Map<String, Object> condicional = new HashMap<>();
                                                    condicional.put("idcontrolasistparcial", listControlAsistenciaParcialFiltrado.get(0).getIdcontrolasistparcial());

                                                    Map<String, Object> parametros = new HashMap<>();
                                                    parametros.put("idcontrolasistparcial", listControlAsistenciaParcialFiltrado.get(0).getIdcontrolasistparcial());
                                                    parametros.put("fechaproceso", new Date());
//                                            parametros.put("horarioingreso", dto.getHorarioingreso());
//                                            parametros.put("horariosalida", dto.getHorariosalida());
//                                            parametros.put("ingreso", dto.getIngreso());
//                                            parametros.put("salida", dto.getSalida());
//                                            parametros.put("minutotardanza", dto.getMinutotardanza());
//                                            parametros.put("minutoextrasalida", dto.getMinutoextrasalida());
//                                            parametros.put("minutofaltasalida", dto.getMinutofaltasalida());
                                                    parametros.put("horastrabajadasremoto", horastrabajadasremoto);
                                                    parametros.put("horastrabajadaspresencial", horastrabajadaspresencial);
                                                    parametros.put("horastrabajadas", aux.getHorastrabajadas());
                                                    parametros.put("horasacompensar", 0);
                                                    parametros.put("horasatrabajar", aux.getHorasatrabajar());
                                                    parametros.put("horasrestante", (horasatrabajar - horastrabajadas) > 0 ? (horasatrabajar - horastrabajadas) : 0);
                                                    parametros.put("minutospermisocongoce", minutospermisocongoce);
                                                    parametros.put("minutospermisosingoce", minutospermisosingoce);
                                                    parametros.put("horaslicenciacongoce", horaslicenciacongoce);
                                                    parametros.put("horaslicenciasingoce", horaslicenciasingoce);
                                                    parametros.put("horascomisiondeservicio", horascomisiondeservicio);
                                                    parametros.put("minutossalud", minutossalud);
                                                    parametros.put("horasvacaciones", horasvacaciones);
                                                    parametros.put("horascapacitacion", horascapacitacion);
                                                    parametros.put("documentosjson", arrayDocumentosJSON.length() != 0 ? arrayDocumentosJSON.toString() : null);
                                                    parametros.put("diasustentada", diaregularizado);
                                                    parametros.put("diasolicitada", diasolicitado);
                                                    parametros.put("esferiado", false);
                                                    parametros.put("fechamodificacion", new Date());

                                                    oReporteMarcacionDao.updateQuery(TControlAsistParcial.class.getSimpleName(), parametros, condicional);
/*****************************************************************************************************************/
                                                }

                                            }

                                        }





                                    }

                                }


                            }


                        } catch (Exception e) {
                            log.info(e.toString());
                        }


                    });


                    return null;
                }).
                collect(Collectors.toList());


        RestResponse holaq = new RestResponse("hola", "prueba", "object");

        return holaq;


    }


    private boolean isWithinRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        if (date == null || startDate == null || endDate == null) {
            return false;
        } else {

            //log.info(date.toString() + " "+ startDate.toString()+" "+endDate.toString());
            return date.isBefore(startDate) && date.isAfter(endDate);
        }
    }


}
