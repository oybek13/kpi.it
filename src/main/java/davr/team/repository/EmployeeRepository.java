package davr.team.repository;

import davr.team.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Oybek Karimjanov
 * Date : 5.12.2022
 * Project Name : kpi.it
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByPassSerialAndPassNumber(String passSerial, int passNumber);
}
