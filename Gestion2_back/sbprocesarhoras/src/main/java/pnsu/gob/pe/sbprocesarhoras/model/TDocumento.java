package pnsu.gob.pe.sbprocesarhoras.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "T_DOCUMENTO")
public class TDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDOCUMENTO")
    private Integer iddocumento;
    @Column(name = "IDPERSONALRESPONSABLE")
    private Integer idpersonalresponsable;
    @Column(name = "IDTTIPODOCUMENTO")
    private Integer idttipodocumento;
    @Column(name = "IDTASUNTO")
    private Integer idtasunto;
    @Column(name = "IDTESTADODOCUMENTO")
    private Integer idtestadodocumento;
    @Column(name = "IDTFUNCIONAREA")
    private Integer idtfuncionarea;
    @Column(name = "IDFERIADO")
    private Integer idferiado;
    @Column(name = "FECHAINICIO")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Column(name = "FECHAOCURRENCIA")
    @Temporal(TemporalType.DATE)
    private Date fechaocurrencia;
    @Column(name = "FECHAFIN")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @Column(name = "FECHARSPTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharspta;
    @Lob
    @Size(max = 65535)
    @Column(name = "COMENTARIO")
    private String comentario;
    @Size(max = 500)
    @Column(name = "DESCASUNTO")
    private String descasunto;
    @Column(name = "HORASALIDA")
    @Temporal(TemporalType.TIME)
    private Date horasalida;
    @Column(name = "HORARETORNO")
    @Temporal(TemporalType.TIME)
    private Date horaretorno;
    @Column(name = "ENVIO")
    private Integer envio;
    @Size(max = 250)
    @Column(name = "DIRECCIONORIGEN")
    private String direccionorigen;
    @Size(max = 250)
    @Column(name = "DIRECCIONDESTINO")
    private String direcciondestino;
    @Column(name = "FECHANACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechanacimiento;
    @Column(name = "VALMASLACTANTES")
    private Integer valmaslactantes;
    @Size(max = 500)
    @Column(name = "DIAGNOSTICO")
    private String diagnostico;
    @Size(max = 300)
    @Column(name = "PROCEDENCIA")
    private String procedencia;
    @Column(name = "HORAS")
    private Integer horas;
    @Column(name = "HORARIOINGRESO")
    @Temporal(TemporalType.TIME)
    private Date horarioingreso;
    @Column(name = "HORARIOSALIDA")
    @Temporal(TemporalType.TIME)
    private Date horariosalida;
    @Lob
    @Size(max = 65535)
    @Column(name = "DETALLEACCIONESJSON")
    private String detalleaccionesjson;
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
    @Size(max = 100)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Column(name = "ESTADO")
    private Integer estado;

    /*
    //    @OneToMany(mappedBy = "iddocumento")
//    private List<TCompensacion> tCompensacionList;
    @OneToMany(targetEntity = TCompensacion.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "IDDOCUMENTO", referencedColumnName = "iddocumento")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<TCompensacion> tCompensacionList = new ArrayList<>();
    @OneToMany(mappedBy = "iddocumento")
    private List<TMovDocumento> tMovDocumentoList;
    //    @OneToMany(mappedBy = "iddocumento")
//    private List<TRsptaDocumento> tRsptaDocumentoList;
    @OneToMany(targetEntity = TRsptaDocumento.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "IDDOCUMENTO", referencedColumnName = "iddocumento")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<TRsptaDocumento> tRsptaDocumentoList = new ArrayList<>();
    @JoinColumn(name = "IDDEPENDENCIA", referencedColumnName = "IDDEPENDENCIA")
    @ManyToOne
    private TDependencia iddependencia;
    @JoinColumn(name = "IDPERSONAL", referencedColumnName = "IDPERSONAL")
    @ManyToOne
    private TPersonal idpersonal;

*/

}
