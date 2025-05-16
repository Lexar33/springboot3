package pe.gob.pnsu.controlasistenciaws.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="T_FERIADO")
public class TFeriado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDFERIADO")
    private Long idferiado;

    @Column(name = "IDTESTADO")
    private Integer idtestado;

    @Column(name = "FECHADESDE")
    @Temporal(TemporalType.DATE)
    private Date fechadesde;

    @Column(name = "FECHAHASTA")
    @Temporal(TemporalType.DATE)
    private Date fechahasta;

    @Size(max = 300)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Size(max = 200)
    @Column(name = "DOCUMENTOREFERENCIA")
    private String documentoreferencia;

    @Column(name = "FECHAINICIORECUPER")
    @Temporal(TemporalType.DATE)
    private Date fechainiciorecuper;

    @Column(name = "FECHAFINRECUPER")
    @Temporal(TemporalType.DATE)
    private Date fechafinrecuper;

    @Column(name = "ESCOMPENSABLE")
    private Boolean escompensable;

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

}
