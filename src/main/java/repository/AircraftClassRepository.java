package repository;

import model.AircraftClassData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AircraftClassRepository extends JpaRepository<AircraftClassData, Integer> {
    List<AircraftClassData> findByName(String name);
}
