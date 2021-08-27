package com.mastery.java.task.dao.impl;

import com.mastery.java.task.config.entity.EmployeeEntity;
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

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<EmployeeEntity> findAll() {
        String findAllSql =
                "SELECT * FROM employee";
        return jdbcTemplate.query(findAllSql, new EmployeeMapper());
    }

    @Override
    @SneakyThrows
    public EmployeeEntity addEmployee(EmployeeEntity employeeEntity) {
        String addEmployeeSql =
                "INSERT INTO employee (first_name, last_name, department_id, job_title, gender, date_of_birth) VALUES (?,?,?,?,cast (? as gender),?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(addEmployeeSql, new String[]{"employee_id"});
                ps.setString(1, employeeEntity.getFirstName());
                ps.setString(2, employeeEntity.getLastName());
                ps.setInt(3, employeeEntity.getDepartmentId());
                ps.setString(4, employeeEntity.getJobTitle());
                ps.setObject(5, employeeEntity.getGender().name());
                ps.setDate(6, Date.valueOf(employeeEntity.getDateOfBirth()));
                return ps;
            }
        }, keyHolder);
        employeeEntity.setEmployeeId(keyHolder.getKey().intValue());
        return employeeEntity;
    }

    @Override
    public void deleteEmployee(int id) {
        String deleteEmployeeSql =
                "DELETE FROM employee WHERE employee_id=?";
        jdbcTemplate.update(deleteEmployeeSql, id);
    }

    @Override
    public void updateInfoAboutEmployee(EmployeeEntity employeeEntity) {
        String updateUserSql =
                "UPDATE employee SET first_name=?, last_name=?, department_id=?, job_title=?, gender=?, date_of_birth=? WHERE employee_id=?";
        jdbcTemplate.update(updateUserSql, employeeEntity.getFirstName(),
                employeeEntity.getLastName(),
                employeeEntity.getDepartmentId(),
                employeeEntity.getJobTitle(),
                employeeEntity.getGender(),
                employeeEntity.getDateOfBirth(),
                employeeEntity.getEmployeeId());
    }

    @Override
    public Optional<Employee> findById(int id) {
        String findEmployeeById =
                "SELECT * FROM employee WHERE employee_id = ?";
        EmployeeEntity employeeEntity = jdbcTemplate.queryForObject(findEmployeeById, new EmployeeMapper(), id);

        return Optional.of(new Employee(employeeEntity.getEmployeeId(), employeeEntity.getFirstName(), employeeEntity.getGender()));
    }
}
