package repository;

import model.CompanyRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRoleRepository extends JpaRepository<CompanyRole, Integer> {

    List<CompanyRole> findByName(String name);

    List<CompanyRole> findAllByOrderByIdAsc();
}
