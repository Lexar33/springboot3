package pnsu.gob.pe.sbprocesarhoras.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="T_MARCACION_PERSONAL")
public class TMarcacionPersonal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDMARCACIONPERSONAL")
    private Long idmarcacionpersonal;
    @Column(name = "IDPERSONAL")
    private Integer idpersonal;
    @Size(max = 100)
    @Column(name = "IPMARCADOR")
    private String ipmarcador;
    @Size(max = 100)
    @Column(name = "IPMARCADORSALIDA")
    private String ipmarcadorsalida;
    @Size(max = 30)
    @Column(name = "DOCUMENTOIDENTIDAD")
    private String documentoidentidad;
    @Column(name = "HORARIOINGRESO")
    @Temporal(TemporalType.TIME)
    private Date horarioingreso;
    @Column(name = "HORARIOSALIDA")
    @Temporal(TemporalType.TIME)
    private Date horariosalida;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
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
    @Column(name = "HORASTRABAJADAS")
    private Integer horastrabajadas;
    @Column(name = "HORASCOMPENSADAS")
    private Integer horascompensadas;
    @Column(name = "FLAGMODIFICACION")
    private Integer flagmodificacion;
    @Size(max = 150)
    @Column(name = "MARCACIONES")
    private String marcaciones;
    @Column(name = "IDAREA")
    private Integer idarea;
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
    @Size(max = 400)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Column(name = "ESTADO")
    private Integer estado;
    //@OneToMany(mappedBy = "idmarcacionpersonal")
    //private List<TCompensacionMarcacion> tCompensacionMarcacionList;
}
