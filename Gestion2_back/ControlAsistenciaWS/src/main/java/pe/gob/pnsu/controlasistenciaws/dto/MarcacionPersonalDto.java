package pe.gob.pnsu.controlasistenciaws.dto;

import jakarta.ejb.Local;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcacionPersonalDto {

    private int idpersonal;
    private String descdependencia;
    private String nombres;
    private String documentoidentidad;
    private Long idmarcacionpersonal;
    private String dia;
    private String sfecha;
    private LocalTime horarioingreso;
    private LocalTime horariosalida;
    private LocalDate fecha;
    private LocalTime ingreso;
    private String sedeingreso;
    private LocalTime salida;
    private String sedesalida;
    private Integer minutotardanza;
    private Integer minutoextrasalida;
    private Integer minutofaltasalida;
    private Integer horasacompensar;
    private Integer horascompensadas;
    private Long jornada;
    private String detallejornada;
    private String detallecompleto;
    private boolean diasustentada;
    private boolean diasolicitada;
    private boolean esferiado;
    private String singreso;
    private String ssalida;

    public MarcacionPersonalDto(int idpersonal,
                                String descdependencia,
                                String nombres,
                                String documentoidentidad,
                                Long idmarcacionpersonal,
                                String dia,
                                String sfecha,
                                LocalDate fecha,
                                LocalTime ingreso,
                                String sedeingreso,
                                LocalTime salida,
                                String sedesalida,
                                Integer minutotardanza,
                                Integer minutoextrasalida,
                                Integer minutofaltasalida,
                                Long jornada,
                                LocalTime horarioingreso,
                                LocalTime horariosalida,
                                String singreso,
                                String ssalida



        ){
        this.idpersonal = idpersonal;
        this.descdependencia = descdependencia;
        this.nombres = nombres;
        this.documentoidentidad = documentoidentidad;
        this.idmarcacionpersonal = idmarcacionpersonal;
        this.dia = dia;
        this.sfecha = sfecha;
        this.fecha = fecha;
        this.ingreso = ingreso;
        this.sedeingreso = sedeingreso;
        this.salida = salida;
        this.sedesalida = sedesalida;
        this.minutotardanza = minutotardanza;
        this.minutoextrasalida = minutoextrasalida;
        this.minutofaltasalida = minutofaltasalida;
        this.jornada = jornada;
        this.horarioingreso = horarioingreso;
        this.horariosalida = horariosalida;
        this.singreso = singreso;
        this.ssalida = ssalida;


    }


}
