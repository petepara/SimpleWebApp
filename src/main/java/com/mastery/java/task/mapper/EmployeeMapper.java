package com.mastery.java.task.mapper;

import com.mastery.java.task.config.entity.EmployeeEntity;
import com.mastery.java.task.config.entity.Gender;
import com.mastery.java.task.dto.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class EmployeeMapper implements RowMapper<EmployeeEntity> {
    @Override
    public EmployeeEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setEmployeeId(resultSet.getInt("employee_id"));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setDepartmentId(resultSet.getInt("department_id"));
        employee.setJobTitle(resultSet.getString("job_title"));
        employee.setGender(Gender.valueOf(resultSet.getString("gender")));
        employee.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
        return employee;
    }
}

