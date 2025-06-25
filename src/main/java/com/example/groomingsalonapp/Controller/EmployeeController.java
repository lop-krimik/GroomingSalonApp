package com.example.groomingsalonapp.Controller;

import com.example.groomingsalonapp.DTO.EmployeeDto;
import com.example.groomingsalonapp.Domain.Employee;
import com.example.groomingsalonapp.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/createEmployee")
    public EmployeeDto createEmployee(@RequestBody Employee employee){
       return employeeService.createEmployee(employee);
    }

    @GetMapping("/getFreeSlots/{employeeId}")
    public List<LocalTime> findFreeSlots(@PathVariable Long employeeId,
                                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDate date){
        if (date == null) {
            date = LocalDate.now();
        }
       return employeeService.findFreeSlots(employeeId, date);
    }
}
