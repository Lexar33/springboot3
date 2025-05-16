package pnsu.gob.pe.sbprocesarhoras.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pnsu.gob.pe.sbprocesarhoras.model.TCompensacionHoras;
import pnsu.gob.pe.sbprocesarhoras.model.TMarcacionPersonal;

import java.time.LocalDate;
import java.util.List;

public interface IMarcacionPersonalRepo extends IGenericRepo<TMarcacionPersonal,Integer>{

    List<TMarcacionPersonal> findByIdpersonalAndFecha(Integer idpersonal, LocalDate fecha);

    /*
    @Query(value = "SELECT * FROM T_MARCACION_PERSONAL t where t.IDPERSONAL =:IDPERSONAL LIMIT 1",nativeQuery = true)
    TMarcacionPersonal listMarcacionPersonal(@Param("IDPERSONAL") Integer IDPERSONAL);
*/
}
