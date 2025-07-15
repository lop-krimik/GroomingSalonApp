package com.example.groomingsalonapp.ExceptiionHandler.EmployeeException;

public class EmployeeNotFoundException extends EmployeeException{
    public EmployeeNotFoundException(Long employeeId) {
        super("Employee with id " + employeeId + " was not found");
    }
}
