package pnsu.gob.pe.sbprocesarhoras.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "T_DEPENDENCIA")
public class TDependencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDEPENDENCIA")
    private Integer iddependencia;
    @Column(name = "IDPERSONALRESPONSABLE")
    private Integer idpersonalresponsable;
    @Column(name = "IDDEPENDSUPERIOR")
    private Integer iddependsuperior;
    @Column(name = "IDTTIPODEPENDENCIA")
    private Integer idttipodependencia;
    @Size(max = 50)
    @Column(name = "CODIGODEPENDENCIA")
    private String codigodependencia;
    @Size(max = 100)
    @Column(name = "DESCDEPENDENCIA")
    private String descdependencia;
    @Size(max = 100)
    @Column(name = "DESCDEPENDDEROGADA")
    private String descdependderogada;
    @Size(max = 80)
    @Column(name = "NUMOFICIOVIRTUAL")
    private String numoficiovirtual;
    @Size(max = 80)
    @Column(name = "NUMMEMOVIRTUAL")
    private String nummemovirtual;
    @Size(max = 30)
    @Column(name = "DNICOORDINADOR")
    private String dnicoordinador;
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
    @Size(max = 300)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Column(name = "ESTADO")
    private Integer estado;
  /*
    @OneToMany(mappedBy = "iddependencia")
    private List<TDocumento> tDocumentoList;
*/

}
