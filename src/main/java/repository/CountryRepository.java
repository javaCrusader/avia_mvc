package repository;

import model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    Country findByName(String name);

    List<Country> findAllByOrderByNameAsc();
}
