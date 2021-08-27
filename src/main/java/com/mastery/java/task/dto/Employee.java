package com.mastery.java.task.dto;

import com.mastery.java.task.config.entity.Gender;
import lombok.*;

@Value
public class Employee {
    Integer employeeId;
    String firstName;
    Gender gender;
}
