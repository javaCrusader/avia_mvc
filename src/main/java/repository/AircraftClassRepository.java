package repository;

import model.AircraftClassData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AircraftClassRepository extends JpaRepository<AircraftClassData, Integer> {
    List<AircraftClassData> findByName(String name);

    Optional<AircraftClassData> findOneByName(String name);

    List<AircraftClassData> findAllByOrderByIdAsc();
}
