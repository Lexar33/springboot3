package pnsu.gob.pe.sbprocesarhoras.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pnsu.gob.pe.sbprocesarhoras.model.TCompensacionHoras;
import pnsu.gob.pe.sbprocesarhoras.model.TMarcacionPersonal;
import pnsu.gob.pe.sbprocesarhoras.model.TPersonal;
import pnsu.gob.pe.sbprocesarhoras.util.EAIUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class taskService {

    private final ICompHorasService compHorasService;
    private final IMarcPersonalService marcPersonalService;

    private final EAIUtil util;

    public void execute (){
        // LISTA TODAS LAS THORASCOMPENSACIÓN QUE SON NULL
        List<TCompensacionHoras> listTCHoras= compHorasService.listAllNull();

        listTCHoras.forEach(
                (TCompensacionHoras elist)->{

                    TPersonal IdPersonal= elist.getIdpersonal();
                    LocalDate fecha = util.convertToLocalDateViaMilisecond(elist.getFecha());
                    String idCH= elist.getIdcompensacionhoras().toString();

                    LocalDateTime horaInicioSobre =util.convertToLocalDateTimeViaMilisecond(elist.getHorainicio());
                    LocalDateTime horaFinSobre =util.convertToLocalDateTimeViaMilisecond(elist.getHorafin());

                    List<TMarcacionPersonal> tMarcacionPersonal = marcPersonalService.listbyIdPersonalFecha(IdPersonal.getIdpersonal(),fecha);
                    if (tMarcacionPersonal != null && !tMarcacionPersonal.isEmpty()){
                        TMarcacionPersonal tMarca= tMarcacionPersonal.get(0);

                        if (tMarca.getIngreso() != null && tMarca.getSalida() !=null) {
                            LocalDateTime ingreso= util.convertToLocalDateTimeViaMilisecond(tMarca.getIngreso());
                            LocalDateTime salida= util.convertToLocalDateTimeViaMilisecond(tMarca.getSalida());

                            log.info("idpersonal:"+IdPersonal.getIdpersonal().toString()+" inicioSobre:"+horaInicioSobre.toString()+" finSobre:"+horaFinSobre.toString()+
                                    "ingreso:"+ingreso.toString()+" salida:"+salida.toString() );

                            if (horaInicioSobre.isAfter(ingreso) && horaFinSobre.isBefore(salida)){

                                log.info("==============================cumple==============================");

                                Integer status=compHorasService.updateTComp(elist.getIdcompensacionhoras(),123);
                                log.info(String.valueOf(status));

                            }else {
                                log.info("id "+idCH+" :existe un error, no cumple");
                            }

                        }else {
                            log.info("id "+idCH+" :no tiene ingreso o salida registrado");
                        }
                    }else
                        log.info("id "+idCH+" :el usuario no tiene marcacion en t_marcacion_personal");


                }
        );
        /*
        for (TCompensacionHoras elist: listTCHoras){

            TPersonal IdPersonal= elist.getIdpersonal();

        }

         */



    }



}
