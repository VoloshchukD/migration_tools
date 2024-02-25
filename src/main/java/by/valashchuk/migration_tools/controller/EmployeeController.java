package by.valashchuk.migration_tools.controller;

import by.valashchuk.migration_tools.Company;
import by.valashchuk.migration_tools.Employee;
import by.valashchuk.migration_tools.repository.CompanyRepository;
import by.valashchuk.migration_tools.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PostMapping("/{employeeId}")
    public Employee addEmployeeToCompany(@PathVariable Long employeeId, @RequestParam Long companyId) {
        Employee employee = new Employee();
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            employee = employeeOptional.get();
            Optional<Company> companyOptional = companyRepository.findById(companyId);
            if (companyOptional.isPresent()) {
                employee.setCompany(companyOptional.get());
                employeeRepository.save(employee);
            }
        }
        return employee;
    }
}


