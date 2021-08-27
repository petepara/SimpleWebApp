package com.mastery.java.task.service.impl;

import com.mastery.java.task.config.entity.EmployeeEntity;
import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll()
                .stream()
                .map(employeeEntity -> new Employee(employeeEntity.getEmployeeId(), employeeEntity.getFirstName(), employeeEntity.getGender()))
                .collect(toList());
    }

    @Override
    public EmployeeEntity addEmployee(EmployeeEntity employeeEntity) {
        return employeeDao.addEmployee(employeeEntity);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeDao.deleteEmployee(id);
    }

    @Override
    public void updateInfoAboutEmployee(EmployeeEntity employeeEntity) {
        employeeDao.updateInfoAboutEmployee(employeeEntity);
    }

    @Override
    public Optional<Employee> findById(int id) {
        if (employeeDao.findById(id).isPresent()) {
            return Optional.of(employeeDao.findById(id).get());
        }
        return Optional.empty();
    }
}
