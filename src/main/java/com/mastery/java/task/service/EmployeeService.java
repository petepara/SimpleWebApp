package com.mastery.java.task.service;

import com.mastery.java.task.dto.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> findAll();

    Employee addEmployee(Employee employee);

    void deleteEmployee(long id);

    Employee updateInfoAboutEmployee(Employee employee);

    Optional<Employee> findById(long id);
}
