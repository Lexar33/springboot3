package pe.gob.pnsu.controlasistenciaws.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestResponse {

    private String codigo;
    private String mensaje;
    private Object object;
}
