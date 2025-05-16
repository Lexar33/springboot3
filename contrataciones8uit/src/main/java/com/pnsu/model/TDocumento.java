package com.pnsu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@Table(name = "T_DOCUMENTO")
@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDOCUMENTO")
    private Integer iddocumento;
    @Column(name = "IDDEPENDENCIA")
    private Integer iddependencia;
    @Column(name = "IDTTIPOREGISTRO")
    private Integer idttiporegistro;
    @Column(name = "ANIO")
    private Integer anio;
    @Column(name = "NUMEROORDEN")
    private Integer numeroorden;

    @Column(name = "NUMEROREGISTRO")
    private String numeroregistro;
    @Column(name = "FECHAAUTENTICACION")
    @Temporal(TemporalType.DATE)
    private Date fechaautenticacion;
    @Column(name = "NUMEROFOLIOS")
    private Integer numerofolios;

    @Column(name = "DENOMINACIONDOCUMENTO")
    private String denominaciondocumento;

    @Column(name = "NOMBRESAPELLIDOS")
    private String nombresapellidos;

    @Column(name = "NOMBRETRAMITE")
    private String nombretramite;

    @Column(name = "DEPENDENCIA")
    private String dependencia;

    @Column(name = "DOCUMENTOIDENTIDAD")
    private String documentoidentidad;

    @Column(name = "URLFIRMA")
    private String urlfirma;
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



    public TDocumento(Integer iddocumento) {
        this.iddocumento = iddocumento;
    }

}
