package com.mastery.java.task.service.impl;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }

    @Override
    public void deleteEmployee(long id) {
        employeeDao.deleteEmployee(id);
    }

    @Override
    public Employee updateInfoAboutEmployee(Employee employee) {
        Employee existingEmployee = employeeDao.findById(employee.getEmployeeId()).get();
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setDepartmentId(employee.getDepartmentId());
        existingEmployee.setJobTitle(employee.getJobTitle());
        existingEmployee.setGender(employee.getGender());
        existingEmployee.setDateOfBirth(employee.getDateOfBirth());
        employeeDao.updateEmployee(existingEmployee);
        return existingEmployee;
    }

    @Override
    public Optional<Employee> findById(long id) {
        if (employeeDao.findById(id).isPresent()) {
            return Optional.of(employeeDao.findById(id).get());
        }
        return Optional.empty();
    }
}
