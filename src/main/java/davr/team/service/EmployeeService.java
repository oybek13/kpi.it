package davr.team.service;

import davr.team.exception.ResourceNotFoundException;
import davr.team.exception.SuccessfulException;
import davr.team.repository.EmployeeRepository;
import davr.team.dto.request.EmployeeDto;
import davr.team.entity.Employee;
import davr.team.exception.ResourceAlreadyExistedException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Oybek Karimjanov
 * Date : 5.12.2022
 * Project Name : kpi.it
 */
@Service
@RequiredArgsConstructor
@Builder
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        boolean result = employeeRepository.existsByPassSerialAndPassNumber(employeeDto.getPassportSerial(), employeeDto.getPassportNumber());
        if (result) {
            throw new ResourceAlreadyExistedException("К сожалению, этот сотрудник уже существует!");
        }

        return EmployeeDto.toDto(
                employeeRepository.save(Employee.builder()
                        .firstName(employeeDto.getFirstName())
                        .lastName(employeeDto.getLastName())
                        .birthDay(employeeDto.getBirthDay())
                        .passSerial(employeeDto.getPassportSerial())
                        .passNumber(employeeDto.getPassportNumber())
                        .cardHolder(employeeDto.getPlasticType())
                        .nciNumber(employeeDto.getNciNumber())
                        .salary(employeeDto.getSalary())
                        .position(employeeDto.getPosition())
                        .level(employeeDto.getLevel())
                        .active(true)
                        .build())
        );
    }


    public List<Employee> getAllEmp() {
        return employeeRepository.findAll();
    }


    public EmployeeDto getOne(long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee: ", "id", id));
        return EmployeeDto.toDto(employee);
    }

    public EmployeeDto editEmp(Long id, EmployeeDto employeeDto){
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee: ", "id", id));

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setPassSerial(employeeDto.getPassportSerial());
        employee.setPassNumber(employeeDto.getPassportNumber());
        employee.setCardHolder(employeeDto.getPlasticType());
        employee.setNciNumber(employeeDto.getNciNumber());
        employee.setSalary(employeeDto.getSalary());
        employee.setPosition(employeeDto.getPosition());
        employee.setLevel(employeeDto.getLevel());
        employee.setActive(true);

        return EmployeeDto.toDto(employeeRepository.save(employee));
    }

    public String deleteEmp(Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee: ", "id", id));
        employee.setActive(false);
        employeeRepository.save(employee);
        throw new SuccessfulException("Employee successfully deleted!");
    }

}
