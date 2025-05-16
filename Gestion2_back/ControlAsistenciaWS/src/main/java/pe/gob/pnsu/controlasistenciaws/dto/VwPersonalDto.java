package pe.gob.pnsu.controlasistenciaws.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VwPersonalDto {

    private int idpersona;
    private String nombres;
    private String apellidopaterno;
    private String apellidomaterno;
    private String documentoidentidad;
    private String fechanacimiento;
    private Integer idpersonal;
    private Integer idtestado;
    private String urlimagen;
    private Integer idsede;
    private String descripcionsede;
    private Integer idcontrato;
    private String fechainicio;
    private LocalDate dfechainiciocontrato;
    private String fechafin;
    private LocalDate dfechafincontrato;
    private String fechacese;
    private LocalDate dfechacesecontrato;
    private String nombrecargo;
    private Double remuneracion;
    private Integer iddependencia;
    private String unidad;
    private String areaequipo;
    private String genero;
    private String tipodoc;
    private String estadopersonal;
    private String tipocontrato;
    private String url;
    private String fechainicioadenda;
    private String fechafinadenda;
    private LocalDate dfechafinadenda;
    private String fechabajaadenda;
    private LocalDate dfechabajaadenda;
    private Integer exoneradomarcar;
    private String correoinstitucional;
    private Integer idhorariotrabajo;
    private Date horarioingreso;
    private Date horariosalida;
    private String iddependenciaencargado;
    private String iddependenciadelegado;
    private String iddependenciadesignado;
    private String iddependenciaencargadosuperior;
    private String iddependenciadelegadosuperior;
    private String iddependenciadesignadosuperior;
    private String nrocontrato;
    private String ruc;
    private String nroanexopnsu;
    private String nrocelularpnsu;
    private Boolean sync;

    public VwPersonalDto(int idpersona, String nombres, String apellidopaterno, String apellidomaterno, String documentoidentidad,
                         Integer idpersonal, Integer idtestado, Integer idsede, String descripcionsede, Integer idcontrato, String fechainicio,
                         String fechafin, String fechacese, String nombrecargo, Double remuneracion, Integer iddependencia, String unidad, String areaequipo,
                         String estadopersonal, String tipocontrato, String fechainicioadenda, String fechafinadenda, String fechabajaadenda,
                         Integer exoneradomarcar, String correoinstitucional, String iddependenciaencargado, String iddependenciadelegado,
                         String iddependenciadesignado, String iddependenciaencargadosuperior, String iddependenciadelegadosuperior,
                         String iddependenciadesignadosuperior, String nrocontrato, String ruc, String nroanexopnsu, String nrocelularpnsu,
                         String horarioingreso, String horariosalida) {
        this.idpersona = idpersona;
        this.nombres = nombres;
        this.apellidopaterno = apellidopaterno;
        this.apellidomaterno = apellidomaterno;
        this.documentoidentidad = documentoidentidad;
        this.idpersonal = idpersonal;
        this.idtestado = idtestado;
        this.idsede = idsede;
        this.descripcionsede = descripcionsede;
        this.idcontrato = idcontrato;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
        this.fechacese = fechacese;
        this.nombrecargo = nombrecargo;
        this.remuneracion = remuneracion;
        this.iddependencia = iddependencia;
        this.unidad = unidad;
        this.areaequipo = areaequipo;
        this.estadopersonal = estadopersonal;
        this.tipocontrato = tipocontrato;
        this.fechainicioadenda = fechainicioadenda;
        this.fechafinadenda = fechafinadenda;
        this.fechabajaadenda = fechabajaadenda;
        this.exoneradomarcar = exoneradomarcar;
        this.correoinstitucional = correoinstitucional;
        this.iddependenciaencargado = iddependenciaencargado;
        this.iddependenciadelegado = iddependenciadelegado;
        this.iddependenciadesignado = iddependenciadesignado;
        this.iddependenciaencargadosuperior = iddependenciaencargadosuperior;
        this.iddependenciadelegadosuperior = iddependenciadelegadosuperior;
        this.iddependenciadesignadosuperior = iddependenciadesignadosuperior;
        this.nrocontrato = nrocontrato;
        this.ruc = ruc;
        this.nroanexopnsu = nroanexopnsu;
        this.nrocelularpnsu = nrocelularpnsu;
    }
}