package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {
    List<Employee> findAll();

    Employee addEmployee(Employee employee);

    void deleteEmployee(long id);

    void updateEmployee(Employee employee);

    Optional<Employee> findById(long id);
}
