package com.mastery.java.task.service;

import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class EmployeeServiceTest {
    @MockBean
    private EmployeeDao employeeDao;
    @Autowired
    private EmployeeService employeeService;

    @Test
    @DisplayName("Test findById Success")
    void testFindById() {
        // Setup our mock repository
        Employee employee = new Employee(1L,
                "Soso",
                "Assa",
                14,
                "manager",
                Gender.FEMALE,
                LocalDate.of(1941, 5, 30));
        doReturn(Optional.of(employee)).when(employeeDao).findById(1);

        // Execute the service call
        Optional<Employee> returnedEmployee = employeeService.findById(1);

        // Assert the response
        Assertions.assertTrue(returnedEmployee.isPresent(), "Employee was not found");
        Assertions.assertSame(returnedEmployee.get(), employee, "The employee returned was not the same as the mock");
    }

    @Test
    @DisplayName("Test findById Not Found")
    void testFindByIdNotFound() {
        // Setup our mock repository
        doReturn(Optional.empty()).when(employeeDao).findById(8);

        // Execute the service call
        Optional<Employee> returnedWidget = employeeService.findById(8);

        // Assert the response
        Assertions.assertFalse(returnedWidget.isPresent(), "Employee should not be found");
    }

    @Test
    @DisplayName("Test findAll")
    void testFindAll() {
        // Setup our mock repository
        Employee employee1 = new Employee(8L,
                "Kira",
                "Arik",
                15,
                "DJ",
                Gender.FEMALE,
                LocalDate.of(1941, 5, 30));
        Employee employee2 = new Employee(9L,
                "Soso",
                "Assa",
                14,
                "manager",
                Gender.FEMALE,
                LocalDate.of(1941, 5, 30));
        doReturn(Arrays.asList(employee1, employee2)).when(employeeDao).findAll();

        // Execute the service call
        List<Employee> employees = employeeService.findAll();

        // Assert the response
        Assertions.assertEquals(2, employees.size(), "findAll should return 2 employee");
    }

    @Test
    @DisplayName("Test save employee")
    void testAdd() {
        // Setup our mock repository
        Employee employee = new Employee(2L,
                "Soso",
                "Assa",
                14,
                "manager",
                Gender.FEMALE,
                LocalDate.of(1979, 12, 31));
        doReturn(employee).when(employeeDao).addEmployee(any());

        // Execute the service call
        Employee returnedEmployee = employeeService.addEmployee(employee);

        // Assert the response
        Assertions.assertNotNull(returnedEmployee, "The added employee should not be null");
        Assertions.assertEquals(2, returnedEmployee.getEmployeeId(), "ID should be incremented");
    }

}

