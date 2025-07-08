package com.example.groomingsalonapp.Schedule.AppontmentSchedule;

import com.example.groomingsalonapp.Domain.Appointment;
import com.example.groomingsalonapp.Repository.AppointmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AppointmentSchedule {

    private final AppointmentRepository appointmentRepository;

    @Scheduled(fixedRate = 60_000)
    @Transactional
    public void deleteAppointment(){
       LocalDateTime now = LocalDateTime.now();
        appointmentRepository.deleteAppointmentByEndDateTimeBefore(now);
        };
    }
