package davr.team.service;

import davr.team.dto.response.EmployeeResponse;
import davr.team.exception.ResourceNotFoundException;
import davr.team.exception.SuccessfulException;
import davr.team.repository.EmployeeRepository;
import davr.team.dto.request.EmployeeDto;
import davr.team.entity.Employee;
import davr.team.exception.ResourceAlreadyExistedException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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


    public EmployeeResponse getAllEmp(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Employee> employees = employeeRepository.findAll(pageable);
        List<Employee> employeesContent = employees.getContent();

        List<EmployeeDto> content = employeesContent
                .stream()
                .map(EmployeeDto::toDto)
                .collect(Collectors.toList());

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setContent(content);
        employeeResponse.setPageNo(employees.getNumber());
        employeeResponse.setPageSize(employees.getSize());
        employeeResponse.setTotalPages(employees.getTotalPages());
        employeeResponse.setTotalElements(employees.getTotalElements());
        employeeResponse.setLast(employees.isLast());

        return employeeResponse;
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
