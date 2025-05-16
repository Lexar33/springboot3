package pe.gob.pnsu.controlasistenciaws.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import pe.gob.pnsu.controlasistenciaws.model.TControlAsistParcial;

import java.time.LocalDate;
import java.util.List;

@NoRepositoryBean
public interface IGenericRepo <T,ID> extends JpaRepository<T,ID> {

}