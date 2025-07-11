package com.example.groomingsalonapp.Service;

import com.example.groomingsalonapp.DTO.EmployeeDto;
import com.example.groomingsalonapp.Domain.Appointment;
import com.example.groomingsalonapp.Domain.Employee;
import com.example.groomingsalonapp.Domain.Handling;
import com.example.groomingsalonapp.Domain.HandlingName;
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
import java.util.stream.Collectors;

//import static com.example.groomingsalonapp.Domain.Handling.durationHandling;
import static com.example.groomingsalonapp.Domain.Handling.getDurationHandling;

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

    public List<LocalTime> findFreeSlots(Long employeeId, LocalDate date, HandlingName handlingName) {
        Duration slotDuration = Handling.getDurationHandling().get(handlingName);
        if (slotDuration == null) {
            throw new IllegalArgumentException("Unknown handling: " + handlingName);
        }

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found: " + employeeId));

        LocalDateTime dayStart = date.atTime(employee.getWorkStart());
        LocalDateTime dayEnd = date.atTime(employee.getWorkEnd());

        List<Appointment> appointments = appointmentRepository
                .findByEmployee_EmployeeIdAndDateTimeBetween(employeeId, dayStart, dayEnd);

        appointments.sort(Comparator.comparing(Appointment::getDateTime));

        List<LocalDateTime[]> busyIntervals = new ArrayList<>();
        for (Appointment appointment : appointments) {
            busyIntervals.add(new LocalDateTime[] { appointment.getDateTime(), appointment.getEndDateTime() });
        }

        List<LocalTime> freeSlots = new ArrayList<>();
        LocalDateTime slotStart = dayStart;

        while (!slotStart.plus(slotDuration).isAfter(dayEnd)) {
            LocalDateTime slotEnd = slotStart.plus(slotDuration);

            boolean overlaps = false;
            for (LocalDateTime[] busy : busyIntervals) {
                LocalDateTime busyStart = busy[0];
                LocalDateTime busyEnd = busy[1];

                // Проверяем пересечение интервалов
                if (slotStart.isBefore(busyEnd) && slotEnd.isAfter(busyStart)) {
                    overlaps = true;
                    break;
                }
            }

            if (!overlaps) {
                freeSlots.add(slotStart.toLocalTime());
            }

            slotStart = slotStart.plusMinutes(5);
        }

        return freeSlots;
    }


}
