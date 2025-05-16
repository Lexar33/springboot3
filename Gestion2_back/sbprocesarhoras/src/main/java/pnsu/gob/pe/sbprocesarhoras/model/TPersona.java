package pnsu.gob.pe.sbprocesarhoras.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="T_PERSONA")
public class TPersona {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPERSONA")
    private Integer idpersona;
    @Column(name = "IDTSEXO")
    private Integer idtsexo;
    @Column(name = "IDTESTADOCIVIL")
    private Integer idtestadocivil;
    @Column(name = "IDTTIPODOCUMENTOIDENTIDAD")
    private Integer idttipodocumentoidentidad;
    @Column(name = "IDTGRUPOSANGUINEO")
    private Integer idtgruposanguineo;
    @Column(name = "IDTLINEAMOVIL")
    private Integer idtlineamovil;
    @Size(max = 100)
    @Column(name = "NOMBRES")
    private String nombres;
    @Size(max = 100)
    @Column(name = "APELLIDOPATERNO")
    private String apellidopaterno;
    @Size(max = 100)
    @Column(name = "APELLIDOMATERNO")
    private String apellidomaterno;
    @Size(max = 30)
    @Column(name = "DOCUMENTOIDENTIDAD")
    private String documentoidentidad;
    @Column(name = "FECHANACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechanacimiento;
    @Size(max = 100)
    @Column(name = "URLFOTO")
    private String urlfoto;
    @Size(max = 100)
    @Column(name = "DIRECCION")
    private String direccion;
    @Size(max = 20)
    @Column(name = "TELEFONOFIJO")
    private String telefonofijo;
    @Size(max = 100)
    @Column(name = "CORREOELECTRONICO")
    private String correoelectronico;
    @Column(name = "CODPAIS")
    private Integer codpais;
    @Column(name = "CODDEPARTAMENTO")
    private Integer coddepartamento;
    @Column(name = "CODPROVINCIA")
    private Integer codprovincia;
    @Column(name = "CODDISTRITO")
    private Integer coddistrito;
    @Size(max = 100)
    @Column(name = "LUGARNACIMIENTO")
    private String lugarnacimiento;
    @Size(max = 100)
    @Column(name = "TELEFONOMOVIL")
    private String telefonomovil;
    @Size(max = 100)
    @Column(name = "ALERGIAS")
    private String alergias;
    @Size(max = 100)
    @Column(name = "CONTACTOEMERGIAS")
    private String contactoemergias;
    @Size(max = 100)
    @Column(name = "NROCONTACTOEMERGENCIA")
    private String nrocontactoemergencia;
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
    @OneToMany(mappedBy = "idpersona")
    private List<TPersonal> tPersonalList;

    */
}
