package com.mastery.java.task.service;

import com.mastery.java.task.config.entity.EmployeeEntity;
import com.mastery.java.task.dto.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> findAll();

    EmployeeEntity addEmployee(EmployeeEntity employeeEntity);

    void deleteEmployee(int id);

    void updateInfoAboutEmployee(EmployeeEntity employeeEntity);

    Optional<Employee> findById(int id);
}
