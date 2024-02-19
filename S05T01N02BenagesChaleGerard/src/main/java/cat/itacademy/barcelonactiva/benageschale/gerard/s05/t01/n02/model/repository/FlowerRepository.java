package cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n02.model.repository;

import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n02.model.domain.FlowerEntity;
import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n02.model.dto.FlowerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowerRepository extends JpaRepository<FlowerEntity, Long> {
    List<FlowerDTO> findSucursalBy();
}
