package cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n01.model.repository;

import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n01.model.dto.SucursalDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
    List<SucursalDTO> findSucursalBy();
   /* @Query(value = "SELECT * FROM sucursals WHERE sucursals.name LIKE %:q%")
    List<Sucursal> findBySucursalName(@Param("q")String q);*/
}
