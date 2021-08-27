package com.mastery.java.task.rest;

import com.mastery.java.task.config.entity.EmployeeEntity;
import com.mastery.java.task.config.entity.Gender;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/employee/{id}")
    public Optional<Employee> getById(@PathVariable("id") int id) {
        return employeeService.findById(id);
    }

    ;

    @PostMapping("/newEmployee")
    public Employee addNewEmployee(@RequestParam String firstName,
                                   @RequestParam String lastName,
                                   @RequestParam Integer departId,
                                   @RequestParam String jobTitle,
                                   @RequestParam String gender,
                                   @RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthday) {
        EmployeeEntity employeeEntity = new EmployeeEntity(firstName,
                lastName,
                departId,
                jobTitle,
                Gender.valueOf(gender),
                birthday);
        employeeService.addEmployee(employeeEntity);
        return new Employee(employeeEntity.getEmployeeId(), employeeEntity.getFirstName(), employeeEntity.getGender());
    }

    @GetMapping("/deleteEmployee/{id}")
    public List<Employee> deleteEmployee(@PathVariable("id") int id) {
        employeeService.deleteEmployee(id);
        return employeeService.findAll();
    }

}
