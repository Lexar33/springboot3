package pnsu.gob.pe.sbprocesarhoras.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "T_COMPENSACION_HORAS")
public class TCompensacionHoras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCOMPENSACIONHORAS")
    private Integer idcompensacionhoras;

    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(name = "HORAINICIO")
    @Temporal(TemporalType.TIME)
    private Date horainicio;

    @Column(name = "HORAFIN")
    @Temporal(TemporalType.TIME)
    private Date horafin;

    @Column(name="IDTESTADO")
    private Integer idtestado;

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

    @JoinColumn(name = "IDDOCUMENTO", foreignKey = @ForeignKey(name="FK_T_COMPEN_HORAS_T_DOCUME"))
    @ManyToOne
    private TDocumento iddocumento;

    @JoinColumn(name = "IDDOCUMENTOCOMP",foreignKey = @ForeignKey(name="FK_T_COMPEN_HORAS_T_DOCUMECOMP"))
    @ManyToOne
    private TDocumento iddocumentocomp;
}
