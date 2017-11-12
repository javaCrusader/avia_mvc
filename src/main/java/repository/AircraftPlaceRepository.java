package repository;

import model.AircraftPlaceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AircraftPlaceRepository extends JpaRepository<AircraftPlaceInfo, Integer> {
    List<AircraftPlaceInfo> findAllByAircraft_Id(Integer id);
}
