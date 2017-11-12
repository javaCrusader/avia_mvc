package repository;

import model.Aircraft;
import model.AircraftClassData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Integer> {

    List<Aircraft> findByName(String name);

}
