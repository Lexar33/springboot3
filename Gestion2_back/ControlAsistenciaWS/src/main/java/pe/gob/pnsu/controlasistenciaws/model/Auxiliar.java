package pe.gob.pnsu.controlasistenciaws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Auxiliar {

    int minutoscitacion1 = 0;
    int minutoscitacion2 = 0,
            minutoscitacion3 = 0,
            minutoscompensacion1 = 0,
            minutoscompensacion2 = 0,
            minutoscompensacion3 = 0,
            minutoscapacitacionoficializada1 = 0,
            minutoscapacitacionoficializada2 = 0,
            minutoscapacitacionoficializada3 = 0,
            minutospermisocongoceotros1 = 0,
            minutospermisocongoceotros2 = 0,
            minutospermisocongoceotros3 = 0,
            minutoscitamedica1 = 0,
            minutoscitamedica2 = 0,
            minutoscitamedica3 = 0,
            minutoslimametropolitana1 = 0,
            minutoslimametropolitana2 = 0,
            minutoslimametropolitana3 = 0,
            minutoscapmediajornada1 = 0,
            minutoscapmediajornada2 = 0,
            minutoscapmediajornada3 = 0,
            minutosasuntosparticulares1 = 0,
            minutosasuntosparticulares2 = 0,
            minutosasuntosparticulares3 = 0,
            minutosremotoparcial1 = 0;

    int horasatrabajar = 8,
    //horasrestante = 0,
    horastrabajadas = 0,
            horastrabajadaspresencial = 0,
            horastrabajadasremoto = 0,
            minutospermisocongoce = 0,
            minutospermisosingoce = 0,
            horaslicenciacongoce = 0,
            horaslicenciasingoce = 0,
            horascomisiondeservicio = 0,
            minutossalud = 0,
            horasvacaciones = 0,
            horascapacitacion = 0,
            minutoscompensados = 0;

    boolean diaregularizado = false;
    boolean diasolicitado = false;

}
