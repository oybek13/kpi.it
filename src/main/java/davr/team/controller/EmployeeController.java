package davr.team.controller;

import davr.team.dto.request.EmployeeDto;
import davr.team.dto.response.EmployeeResponse;
import davr.team.entity.Employee;
import davr.team.service.EmployeeService;
import davr.team.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Oybek Karimjanov
 * Date : 5.12.2022
 * Project Name : kpi.it
 */
@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> createEmp(@Valid @RequestBody EmployeeDto employeeDto){
        EmployeeDto employee = employeeService.createEmployee(employeeDto);
        return ResponseEntity.ok(employee);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/get")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmp());
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/get-one/{id}")
    public ResponseEntity<?> getById(@PathVariable long id){
        return ResponseEntity.ok(employeeService.getOne(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateEmp(@PathVariable Long id, @Valid @RequestBody EmployeeDto employeeDto){
        return ResponseEntity.ok(employeeService.editEmp(id, employeeDto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        return employeeService.deleteEmp(id);
    }











}
