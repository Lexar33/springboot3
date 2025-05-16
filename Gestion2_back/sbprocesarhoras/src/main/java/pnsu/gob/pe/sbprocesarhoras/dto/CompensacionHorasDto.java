package pnsu.gob.pe.sbprocesarhoras.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompensacionHorasDto {

    private Date fecha;

    @Temporal(TemporalType.TIME)
    private Date horainicio;

    @Temporal(TemporalType.TIME)
    private Date horafin;

}


