package pe.gob.pnsu.controlasistenciaws.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestParcialDto {

    private String idpersonal;
    private String documentoidentidad;
    private String desde;
    private String hasta;
}
