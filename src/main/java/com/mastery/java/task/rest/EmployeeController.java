package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public Optional<Employee> getById(@PathVariable("id") long id) {
        return employeeService.findById(id);
    }

    @PostMapping("/newEmployee")
    public Employee addNewEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/updateEmployee")
    public Employee replaceEmployee(@RequestBody Employee newEmployee) {
        return employeeService.updateInfoAboutEmployee(newEmployee);
    }
    @GetMapping("/deleteEmployee/{id}")
    public List<Employee> deleteEmployee(@PathVariable("id") int id) {
        employeeService.deleteEmployee(id);
        return employeeService.findAll();
    }

}
