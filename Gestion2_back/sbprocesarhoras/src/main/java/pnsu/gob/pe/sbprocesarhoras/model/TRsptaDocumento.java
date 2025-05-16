package pnsu.gob.pe.sbprocesarhoras.model;

import jakarta.persistence.*;

import javax.validation.constraints.Size;
import java.util.Date;

public class TRsptaDocumento {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDRSPTADOCUMENTO")
    private Integer idrsptadocumento;
    @Column(name = "IDTTIPORSPTADOCUMENTO")
    private Integer idttiporsptadocumento;
    @Column(name = "IDPERSONALRESPONSABLE")
    private Integer idpersonalresponsable;
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
    @Lob
    @Size(max = 65535)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Column(name = "ESTADO")
    private Integer estado;
    @JoinColumn(name = "IDDOCUMENTO", referencedColumnName = "IDDOCUMENTO")
    @ManyToOne
    private TDocumento iddocumento;

}
