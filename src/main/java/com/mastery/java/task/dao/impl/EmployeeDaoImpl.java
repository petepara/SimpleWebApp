package com.mastery.java.task.dao.impl;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.mapper.EmployeeMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final String FIND_ALL_EMPLOYEES_SQL = "SELECT * FROM employee";
    private final String ADD_EMPLOYEE_SQL = "INSERT INTO employee (first_name, last_name, department_id, job_title, gender, date_of_birth)" +
            " VALUES (?,?,?,?,cast (? as gender),?)";
    private final String DELETE_EMPLOYEE_SQL = "DELETE FROM employee WHERE employee_id=?";
    private final String UPDATE_EMPLOYEE_SQL = "UPDATE employee SET first_name=?, last_name=?, department_id=?, job_title=?, gender=cast (? as gender), date_of_birth=?" +
            " WHERE employee_id=?";
    private final String FIND_EMPLOYEE_BY_ID_SQL = "SELECT * FROM employee WHERE employee_id = ?";

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(FIND_ALL_EMPLOYEES_SQL, new EmployeeMapper());
    }

    @Override
    @SneakyThrows
    public Employee addEmployee(Employee employee) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(ADD_EMPLOYEE_SQL, new String[]{"employee_id"});
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setInt(3, employee.getDepartmentId());
            ps.setString(4, employee.getJobTitle());
            ps.setObject(5, employee.getGender().name());
            ps.setDate(6, Date.valueOf(employee.getDateOfBirth()));
            return ps;
        }, keyHolder);
        employee.setEmployeeId(keyHolder.getKey().longValue());
        return employee;
    }

    @Override
    public void deleteEmployee(long id) {
        jdbcTemplate.update(DELETE_EMPLOYEE_SQL, id);
    }

    @Override
    public void updateEmployee(Employee employeeEntity) {
        jdbcTemplate.update(UPDATE_EMPLOYEE_SQL,
                employeeEntity.getFirstName(),
                employeeEntity.getLastName(),
                employeeEntity.getDepartmentId(),
                employeeEntity.getJobTitle(),
                employeeEntity.getGender().name(),
                employeeEntity.getDateOfBirth(),
                employeeEntity.getEmployeeId());
    }

    @Override
    public Optional<Employee> findById (long id) {
        Employee employee = jdbcTemplate.queryForObject(FIND_EMPLOYEE_BY_ID_SQL, new EmployeeMapper(), id);
        return Optional.ofNullable(employee);
    }
}
