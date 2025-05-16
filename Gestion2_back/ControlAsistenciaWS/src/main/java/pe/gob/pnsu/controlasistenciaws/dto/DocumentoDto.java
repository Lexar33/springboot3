package pe.gob.pnsu.controlasistenciaws.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoDto {

    private Integer iddocumento;
    private Integer idpersonal;
    private String tipodocumento;
    private String tiposolicitud;
    private String asunto;
    private Integer idtasunto;
    private String estadodocumento;
    private Integer idtestadodocumento;
    private Date fechainicio;
    private Date fechaocurrencia;
    private Date fechafin;
    private String sfechainicio;
    private String sfechaocurrencia;
    private String sfechafin;
    private Date fecharspta;
    private Date horasalida;
    private Date horaretorno;
    private Integer horas;
    private String documentoidentidad;
    private Integer idsuperior;

    public DocumentoDto(Integer iddocumento, String tipodocumento, String tiposolicitud, String asunto, Integer idtasunto, String estadodocumento, Integer idtestadodocumento, Date fechainicio, Date fechaocurrencia, Date fechafin, Date fecharspta, Date horasalida, Date horaretorno, Integer idpersonal, Integer horas, String documentoidentidad) {
        this.iddocumento = iddocumento;
        this.tipodocumento = tipodocumento;
        this.tiposolicitud = tiposolicitud;
        this.asunto = asunto;
        this.idtasunto = idtasunto;
        this.estadodocumento = estadodocumento;
        this.idtestadodocumento = idtestadodocumento;
        this.fechainicio = fechainicio;
        this.fechaocurrencia = fechaocurrencia;
        this.fechafin = fechafin;
        this.fecharspta = fecharspta;
        this.horasalida = horasalida;
        this.horaretorno = horaretorno;
        this.idpersonal = idpersonal;
        this.horas = horas;
        this.documentoidentidad = documentoidentidad;
    }
}
