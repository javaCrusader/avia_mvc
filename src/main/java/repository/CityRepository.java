package repository;

import model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {

    City findByName(String name);

    List<City> findAllByOrderByNameAsc();

    List<City> findAllByCountryNameEquals(String country);

}
