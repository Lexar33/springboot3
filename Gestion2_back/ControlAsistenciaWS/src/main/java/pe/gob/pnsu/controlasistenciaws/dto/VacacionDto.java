package pe.gob.pnsu.controlasistenciaws.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VacacionDto {
    private Integer idvacacion;
    private Integer idpersonal;
    private Integer idttipovacacion;
    private Integer idttipoadelanto;
    private Integer idtestadovacacion;
    private String nromemo;
    private String periodo;
    private String periodomes;
    private String periododetalle;
    private Date fechainicio;
    private Date fechaocurrencia;
    private Date fechafin;
    private String sfechainicio;
    private String sfechaocurrencia;
    private String sfechafin;
    private Date horasalida;
    private String shorasalida;
    private Date horaretorno;
    private String shoraretorno;
    private Double diascorrespondiente;
    private Double diastomada;
    private Double diasrestante;
    private Double horastomada;
    private String documentoreferencia;
    private String comentario;
    private String tipovacacion;
    private String estadovacacion;
    private String documentoidentidad;
    private String nombres;
    private String apellidos;


    public VacacionDto(Integer idvacacion, Integer idpersonal, Integer idttipovacacion, Integer idttipoadelanto, Integer idtestadovacacion, String nromemo, String periodo, String periodomes, String periododetalle, Date fechainicio, Date fechaocurrencia, Date fechafin, String sfechainicio, String sfechaocurrencia, String sfechafin, Date horasalida, Date horaretorno, Double diascorrespondiente, Double diastomada, Double diasrestante, Double horastomada, String documentoreferencia, String comentario, String tipovacacion, String estadovacacion, String documentoidentidad, String nombres, String apellidos) {
        this.idvacacion = idvacacion;
        this.idpersonal = idpersonal;
        this.idttipovacacion = idttipovacacion;
        this.idttipoadelanto = idttipoadelanto;
        this.idtestadovacacion = idtestadovacacion;
        this.nromemo = nromemo;
        this.periodo = periodo;
        this.periodomes = periodomes;
        this.periododetalle = periododetalle;
        this.fechainicio = fechainicio;
        this.fechaocurrencia = fechaocurrencia;
        this.fechafin = fechafin;
        this.sfechainicio = sfechainicio;
        this.sfechaocurrencia = sfechaocurrencia;
        this.sfechafin = sfechafin;
        this.horasalida = horasalida;
        this.horaretorno = horaretorno;
        this.diascorrespondiente = diascorrespondiente;
        this.diastomada = diastomada;
        this.diasrestante = diasrestante;
        this.horastomada = horastomada;
        this.documentoreferencia = documentoreferencia;
        this.comentario = comentario;
        this.tipovacacion = tipovacacion;
        this.estadovacacion = estadovacacion;
        this.documentoidentidad = documentoidentidad;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }
}
