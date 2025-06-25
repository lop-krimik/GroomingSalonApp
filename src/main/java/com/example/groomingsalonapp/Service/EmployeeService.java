package com.example.groomingsalonapp.Service;

import com.example.groomingsalonapp.DTO.EmployeeDto;
import com.example.groomingsalonapp.Domain.Appointment;
import com.example.groomingsalonapp.Domain.Employee;
import com.example.groomingsalonapp.ExceptiionHandler.EmployeeException.EmployeeAlreadyExistsException;
import com.example.groomingsalonapp.ExceptiionHandler.EmployeeException.EmployeeNotFoundException;
import com.example.groomingsalonapp.Repository.AppointmentRepository;
import com.example.groomingsalonapp.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AppointmentRepository appointmentRepository;

    public EmployeeDto createEmployee(Employee employee){

//        employeeRepository.findByEmployeeId(employee.getEmployeeId())
//                .orElseThrow(()-> new EmployeeAlreadyExistsException(
//                        String.format("Employee with id: %d already exists",employee.getEmployeeId()))
//                );
            employeeRepository.save(employee);
           return EmployeeDto.fromEmployeeEntity(employee);

    }

    public Boolean isEmployeeWorking(Long employeeId, String workDay) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found, id: " + employeeId));

        return employee.getWorkDay().contains(workDay);
    }

    public List<LocalTime> findFreeSlots(Long employeeId, LocalDate date) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found: " + employeeId));

        LocalTime workStart = employee.getWorkStart();
        LocalTime workEnd = employee.getWorkEnd();

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        List<Appointment> appointments = appointmentRepository
                .findByEmployee_EmployeeIdAndDateTimeBetween(employeeId, startOfDay, endOfDay);

        appointments.sort(Comparator.comparing(Appointment::getDateTime));

        List<LocalTime> freeSlots = new ArrayList<>();
        LocalTime currentTime = workStart;

        for (Appointment appointment : appointments) {
            LocalTime appointmentStart = appointment.getDateTime().toLocalTime();
            long minutesBetween = Duration.between(currentTime, appointmentStart).toMinutes();

            if (minutesBetween >= 30) {
                while (minutesBetween >= 30) {
                    freeSlots.add(currentTime);
                    currentTime = currentTime.plusMinutes(30);
                    minutesBetween = Duration.between(currentTime, appointmentStart).toMinutes();
                }
            }

            currentTime = appointmentStart.plusMinutes(
                    appointment.getHandling().getDuration().toMinutes()
            );
        }

        if (Duration.between(currentTime, workEnd).toMinutes() >= 30) {
            while (!currentTime.isAfter(workEnd.minusMinutes(30))) {
                freeSlots.add(currentTime);
                currentTime = currentTime.plusMinutes(30);
            }
        }

        return freeSlots;
    }

}
