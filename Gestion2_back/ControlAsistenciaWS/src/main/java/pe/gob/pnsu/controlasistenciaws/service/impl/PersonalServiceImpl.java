package pe.gob.pnsu.controlasistenciaws.service.impl;

import jakarta.ejb.Local;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.gob.pnsu.controlasistenciaws.dto.DocumentoDto;
import pe.gob.pnsu.controlasistenciaws.dto.MarcacionPersonalDto;
import pe.gob.pnsu.controlasistenciaws.dto.VacacionDto;
import pe.gob.pnsu.controlasistenciaws.dto.VwPersonalDto;
import pe.gob.pnsu.controlasistenciaws.model.TControlAsistParcial;
import pe.gob.pnsu.controlasistenciaws.model.TFeriado;
import pe.gob.pnsu.controlasistenciaws.model.TPersonal;
import pe.gob.pnsu.controlasistenciaws.repo.IGenericRepo;
import pe.gob.pnsu.controlasistenciaws.repo.IPersonalRepo;
import pe.gob.pnsu.controlasistenciaws.service.IPersonalService;
import pe.gob.pnsu.controlasistenciaws.util.EAIUtil;

import javax.print.attribute.standard.DocumentName;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonalServiceImpl extends CRUDImpl<TPersonal, Integer> implements IPersonalService {

    private final IPersonalRepo repo;

    private final EAIUtil util;

    @Override
    protected IGenericRepo<TPersonal, Integer> getRepo() {
        return repo;
    }


    @Override
    public List<Object[]> listarPersonal(Integer idpersona) {
        return repo.getPersonaSQL(idpersona);
    }

    @Override
    public List<VwPersonalDto> listarPersonalFechaHasta() {
        List<VwPersonalDto> listDto = repo.callSP().stream().map(
                o -> new VwPersonalDto(
                        (int) o[0],
                        (String) o[1],
                        (String) o[2],
                        (String) o[3],
                        (String) o[4],
                        (Integer) o[5],
                        (Integer) o[6],
                        (Integer) o[7],
                        (String) o[8],
                        (Integer) o[9],
                        (String) o[10],
                        (String) o[11],
                        (String) o[12],
                        (String) o[13],
                        (Double) o[14],
                        (Integer) o[15],
                        (String) o[16],
                        (String) o[17],
                        (String) o[18],
                        (String) o[19],
                        (String) o[20],
                        (String) o[21],
                        (String) o[22],
                        (Integer) o[23],
                        (String) o[24],
                        (String) o[25],
                        (String) o[26],
                        (String) o[27],
                        (String) o[28],
                        (String) o[29],
                        (String) o[30],
                        (String) o[31],
                        (String) o[32],
                        (String) o[33],
                        (String) o[34],
                        (String) o[35],
                        (String) o[36]
                )
        ).collect(Collectors.toList());

        log.info("Cantidad de listPersonal {}", listDto.size());
        log.info("===================================================");

        return listDto;
    }


    @Override
    public List<MarcacionPersonalDto> listarMarcacionPersonal(LocalDate lddesde, LocalDate ldhasta) {
/*
        repo.listarMarcacionPersonal(lddesde, ldhasta).forEach(
                o -> log.info(
                        o[0].getClass().toString() + "\n" +
                                o[1].getClass().toString() + "\n" +
                                o[2].getClass().toString() + "\n" +
                                o[3].getClass().toString() + "\n" +
                                o[4].getClass().toString() + "\n" +
                                o[5].getClass().toString() + "\n" +
                                o[6].getClass().toString() + "\n" +
                                o[7].getClass().toString() + "\n" + //date
                                o[8].getClass().toString() + "\n" + //time
                                o[9].getClass().toString() + "\n" +
                                o[10].getClass().toString() + "\n" + //time
                                o[11].getClass().toString() + "\n" +
                                o[12].getClass().toString() + "\n" +
                                o[13].getClass().toString() + "\n" +
                                o[14].getClass().toString() + "\n" +
                                o[15].getClass().toString() + "\n" +
                                o[16].getClass().toString() + "\n" + //time
                                o[17].getClass().toString() + "\n" + //time
                                o[18].getClass().toString() + "\n" +
                                o[19].getClass().toString() + "\n"
                ));
*/

        List<MarcacionPersonalDto> listDto = repo.listarMarcacionPersonal(lddesde, ldhasta).stream().map(
                o -> {

                    return new MarcacionPersonalDto(
                            (int) o[0],
                            (String) o[1],
                            (String) o[2],
                            (String) o[3],
                            (Long) o[4],
                            (String) o[5],
                            (String) o[6],
                            util.convertToLocalDateFromSqlDate((java.sql.Date) o[7]),
                            util.convertToLocalTimeFromSqlTime((java.sql.Time) o[8]),
                            (String) o[9],
                            util.convertToLocalTimeFromSqlTime((java.sql.Time) o[10]),
                            (String) o[11],
                            (Integer) o[12],
                            (Integer) o[13],
                            (Integer) o[14],
                            (Long) o[15],
                            util.convertToLocalTimeFromSqlTime((java.sql.Time) o[16]),
                            util.convertToLocalTimeFromSqlTime((java.sql.Time) o[17]),
                            (String) o[18],
                            (String) o[19]
                    );

                }
        ).collect(Collectors.toList());

        log.info("Cantidad de listMarcacionPersonal {}", listDto.size());

        log.info("===================================================");

        return listDto;
    }


    @Override
    public List<TFeriado> listFeriado(LocalDate lddesde, LocalDate ldhasta) {
        List<TFeriado> listDto = repo.listarFeriados(lddesde, ldhasta);

        log.info("Cantidad de listFeriado {}", listDto.size());

        log.info("===================================================");

        return listDto;
    }

    @Override
    public List<DocumentoDto> listarDocumentoReporte(LocalDate lddesde, LocalDate ldhasta) {
        List<DocumentoDto> listDto = repo.listarDocumentoReporte(lddesde, ldhasta).stream().map(
                o -> new DocumentoDto(
                        (Integer) o[0],
                        (String) o[1],
                        (String) o[2],
                        (String) o[3],
                        (Integer) o[4],
                        (String) o[5],
                        (Integer) o[6],
                        (Date) o[7],
                        (Date) o[8],
                        (Date) o[9],
                        (Date) o[10],
                        (Date) o[11],
                        (Date) o[12],
                        (Integer) o[13],
                        (Integer) o[14],
                        (String) o[15]
                )
        ).collect(Collectors.toList());
        log.info("Cantidad de listDocumentos {}", listDto.size());
        log.info("===================================================");

        return listDto;
    }

    @Override
    public List<VacacionDto> listarVacacionPersonal(LocalDate lddesde, LocalDate ldhasta) {
        List<VacacionDto> listDto = repo.listarvacacionPersonal(lddesde, ldhasta)
                .stream().map(
                        o -> new VacacionDto(
                                (Integer) o[0], (Integer) o[1], (Integer) o[2], (Integer) o[3], (Integer) o[4],
                                (String) o[5], (String) o[6], (String) o[7], (String) o[8],
                                Optional.ofNullable(o[9]).isPresent() ? (Date) o[9] : null,
                                Optional.ofNullable(o[10]).isPresent() ? (Date) o[10] : null,
                                Optional.ofNullable(o[11]).isPresent() ? (Date) o[11] : null,
                                (String) o[12], (String) o[13], (String) o[14],
                                Optional.ofNullable(o[15]).isPresent() ? (Date) o[15] : null,
                                Optional.ofNullable(o[16]).isPresent() ? (Date) o[16] : null,
                                (Double) o[17], (Double) o[18], (Double) o[19], (Double) o[20],
                                (String) o[21], (String) o[22], (String) o[23], (String) o[24],
                                (String) o[25], (String) o[26], (String) o[27])
                ).collect(Collectors.toList());
        log.info("Cantidad de listVacaciones {}", listDto.size());
        log.info("===================================================");

        return listDto;
    }





}







