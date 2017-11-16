package repository;

import model.AircraftPlaceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftPlaceRepository extends JpaRepository<AircraftPlaceInfo, Integer> {
}
