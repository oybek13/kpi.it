package davr.team.dao;

import davr.team.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Oybek Karimjanov
 * Date : 5.11.2022
 * Project Name : kpi.it
 */
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
