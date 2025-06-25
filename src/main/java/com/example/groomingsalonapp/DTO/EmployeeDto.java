package com.example.groomingsalonapp.DTO;

import com.example.groomingsalonapp.Domain.Employee;
import com.example.groomingsalonapp.Domain.Post;

import java.time.LocalTime;

public record EmployeeDto(
        String employeeName,
        Post post,
        int experienceYears,
        LocalTime workStart,
        LocalTime workEnd,
        String workDay
) {
    public static EmployeeDto fromEmployeeEntity(Employee employee){

        return new EmployeeDto(
                employee.getEmployeeName(),
                employee.getPost(),
                employee.getExperienceYears(),
                employee.getWorkStart(),
                employee.getWorkEnd(),
                employee.getWorkDay()
        );
    }
}
