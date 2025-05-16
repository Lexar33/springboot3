package pe.gob.pnsu.controlasistenciaws.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "T_CONTROL_ASIST_PARCIAL")
public class TControlAsistParcial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCONTROLASISTPARCIAL")
    private Long idcontrolasistparcial;

    @Column(name = "IDTESTADO")
    private Integer idtestado;

    @Size(max = 30)
    @Column(name = "DOCUMENTOIDENTIDAD")
    private String documentoidentidad;

    @Column(name = "FECHAPROCESO")
    @Temporal(TemporalType.DATE)
    private Date fechaproceso;

    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(name = "HORARIOINGRESO")
    @Temporal(TemporalType.TIME)
    private Date horarioingreso;
    @Column(name = "HORARIOSALIDA")
    @Temporal(TemporalType.TIME)
    private Date horariosalida;
    @Column(name = "INGRESO")
    @Temporal(TemporalType.TIME)
    private Date ingreso;
    @Column(name = "SALIDA")
    @Temporal(TemporalType.TIME)
    private Date salida;
    @Column(name = "MINUTOTARDANZA")
    private Integer minutotardanza;
    @Column(name = "MINUTOEXTRASALIDA")
    private Integer minutoextrasalida;
    @Column(name = "MINUTOFALTASALIDA")
    private Integer minutofaltasalida;
    @Column(name = "HORASTRABAJADASREMOTO")
    private Integer horastrabajadasremoto;
    @Column(name = "HORASTRABAJADASPRESENCIAL")
    private Integer horastrabajadaspresencial;
    @Column(name = "HORASTRABAJADAS")
    private Integer horastrabajadas;
    @Column(name = "HORASACOMPENSAR")
    private Integer horasacompensar;
    @Column(name = "HORASATRABAJAR")
    private Integer horasatrabajar;
    @Column(name = "HORASRESTANTE")
    private Integer horasrestante;
    @Column(name = "MINUTOSPERMISOCONGOCE")
    private Integer minutospermisocongoce;
    @Column(name = "MINUTOSPERMISOSINGOCE")
    private Integer minutospermisosingoce;
    @Column(name = "HORASLICENCIACONGOCE")
    private Integer horaslicenciacongoce;
    @Column(name = "HORASLICENCIASINGOCE")
    private Integer horaslicenciasingoce;
    @Column(name = "HORASCOMISIONDESERVICIO")
    private Integer horascomisiondeservicio;
    @Column(name = "MINUTOSSALUD")
    private Integer minutossalud;
    @Column(name = "HORASVACACIONES")
    private Integer horasvacaciones;
    @Column(name = "HORASCAPACITACION")
    private Integer horascapacitacion;
    @Lob
    @Size(max = 65535)
    @Column(name = "DOCUMENTOSJSON")
    private String documentosjson;
    @Lob
    @Size(max = 65535)
    @Column(name = "MARCACIONJSON")
    private String marcacionjson;
    @Column(name = "DIASUSTENTADA")
    private Boolean diasustentada;
    @Column(name = "DIASOLICITADA")
    private Boolean diasolicitada;
    @Column(name = "ESFERIADO")
    private Boolean esferiado;
    @Column(name = "FLAGMODIFICACION")
    private Integer flagmodificacion;
    @Size(max = 400)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Column(name = "IDUSUARIOCREACION")
    private Integer idusuariocreacion;
    @Column(name = "IDUSUARIOMODIFICACION")
    private Integer idusuariomodificacion;
    @Column(name = "FECHACREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Column(name = "FECHAMODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechamodificacion;
    @Column(name = "ESTADO")
    private Integer estado;
    @JoinColumn(name = "IDPERSONAL", referencedColumnName = "IDPERSONAL")
    @ManyToOne
    private TPersonal idpersonal;


    public TControlAsistParcial(Long idcontrolasistparcial,Integer idtestado,String documentoidentidad) {

       this.idcontrolasistparcial =idcontrolasistparcial;
       this.idtestado =idtestado;
       this.documentoidentidad =documentoidentidad;
    }



}
