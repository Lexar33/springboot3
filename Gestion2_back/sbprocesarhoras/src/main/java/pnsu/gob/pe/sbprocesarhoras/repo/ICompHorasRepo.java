package pnsu.gob.pe.sbprocesarhoras.repo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pnsu.gob.pe.sbprocesarhoras.model.TCompensacionHoras;

import java.util.List;

public interface ICompHorasRepo extends IGenericRepo<TCompensacionHoras,Integer> {


    @Query(value = "SELECT * FROM T_COMPENSACION_HORAS t WHERE t.idtestado IS NULL",nativeQuery = true)
    List<TCompensacionHoras> listAllNull();

    @Transactional
    @Modifying
    @Query(value="UPDATE T_COMPENSACION_HORAS t SET t.idtestado= :idtestado WHERE t.idcompensacionhoras = :idcomphoras",nativeQuery = true)
    int updateEstadoCompHoras(@Param("idcomphoras") Integer idcomphoras,@Param("idtestado") Integer idtestado);


}
