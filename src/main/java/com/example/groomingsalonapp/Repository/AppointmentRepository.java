package com.example.groomingsalonapp.Repository;

import com.example.groomingsalonapp.Domain.Appointment;
import com.example.groomingsalonapp.Domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    //List<Appointment> findByEmployee_EmployeeId(Long employeeId);

    List<Appointment> findByEmployee_EmployeeIdAndDateTimeBetween(Long employeeId, LocalDateTime startOfDay, LocalDateTime endOfDay);

    boolean existsByEmployeeAndDateTimeLessThanAndEndDateTimeGreaterThan(Employee employee, LocalDateTime dateTime, LocalDateTime endDateTime);

    void deleteAppointmentByEndDateTimeBefore(LocalDateTime localDateTime);

    List<Appointment> findByClient_ClientId(Long clientId);

    List<Appointment> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);

}