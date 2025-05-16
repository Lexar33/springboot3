package pe.gob.pnsu.controlasistenciaws.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.gob.pnsu.controlasistenciaws.model.TControlAsistParcial;

import java.time.LocalDate;
import java.util.List;

public interface IControlAsistenciaRepo extends IGenericRepo<TControlAsistParcial,Integer> {

    List<TControlAsistParcial> findByFechaGreaterThanEqual(LocalDate desde);



    @Query(value="SELECT * FROM T_CONTROL_ASIST_PARCIAL t where t.fecha between :desde and :hasta",
            nativeQuery = true)
    List<TControlAsistParcial> listarAsistenciaParcial(@Param("desde") LocalDate desde, @Param("hasta") LocalDate hasta);



}
