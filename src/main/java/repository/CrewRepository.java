package repository;

import model.CrewMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrewRepository extends JpaRepository<CrewMember, Integer> {

    List<CrewMember> findByName(String name);

    List<CrewMember> findAllByOrderByIdAsc();

    List<CrewMember> findAllByFunctionNameEqualsAndFlightNull(String function);

    List<CrewMember> findAllByFlightIdEqualsOrderByFunctionId(Integer flightId);
}
