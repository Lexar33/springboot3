package pnsu.gob.pe.sbprocesarhoras.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="T_MOV_DOCUMENTO")
public class TMovDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDMOVDOCUMENTO")
    private Integer idmovdocumento;
    @Column(name = "IDTESTADOMOVIMIENTO")
    private Integer idtestadomovimiento;
    @Column(name = "IDUSUARIOMOVIMIENTO")
    private Integer idusuariomovimiento;
    @Column(name = "FECHAMOVIMEINTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechamovimeinto;
    @Size(max = 500)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Column(name = "ESTADO")
    private Integer estado;
    @JoinColumn(name = "IDDOCUMENTO", referencedColumnName = "IDDOCUMENTO")
    @ManyToOne
    private TDocumento iddocumento;

}
