package pe.gob.pnsu.controlasistenciaws.repo;

import jdk.jfr.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import pe.gob.pnsu.controlasistenciaws.model.TControlAsistParcial;
import pe.gob.pnsu.controlasistenciaws.model.TFeriado;
import pe.gob.pnsu.controlasistenciaws.model.TPersonal;

import java.time.LocalDate;
import java.util.List;

public interface IPersonalRepo  extends IGenericRepo<TPersonal,Integer> {

    @Query(value="SELECT * FROM T_PERSONA c where c.idpersona < :idpersona", nativeQuery = true)
    List<Object[]> getPersonaSQL(@Param("idpersona") Integer idpersona);

    @Query(value="CALL SP_LISTAR_PERSONAL_FECHA_HASTA(null,null,null,null,null,null,null,null)", nativeQuery = true)
    List<Object[]> callSP();

    @Query(value="CALL SP_LISTAR_DOCUMENTO_REPORTE_MARCACION(null,null,null,null,null,:desde,:hasta)", nativeQuery = true)
    List<Object[]> listarDocumentoReporte(@Param("desde") LocalDate desde, @Param("hasta") LocalDate hasta);

    @Query(value="CALL SP_LISTAR_VACACION_PERSONAL(null,null,null,:desde,:hasta,null)", nativeQuery = true)
    List<Object[]> listarvacacionPersonal(@Param("desde") LocalDate desde, @Param("hasta") LocalDate hasta);

    @Query(value="CALL SP_LISTAR_MARCACION_PERSONAL(null,null,:desde,:hasta)", nativeQuery = true )
    List<Object[]> listarMarcacionPersonal(@Param("desde") LocalDate desde, @Param("hasta") LocalDate hasta);

    //JPQL
    @Query(value="SELECT t FROM TFeriado t where (:desde <= t.fechadesde and :hasta >= t.fechahasta ) AND t.estado= 1")
    List<TFeriado> listarFeriados(@Param("desde") LocalDate desde, @Param("hasta") LocalDate hasta);


}
