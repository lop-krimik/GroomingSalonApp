package com.example.groomingsalonapp.Controller;

import com.example.groomingsalonapp.DTO.AppointmentDto;
import com.example.groomingsalonapp.Domain.Appointment;
import com.example.groomingsalonapp.Domain.HandlingName;
import com.example.groomingsalonapp.Service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/createAppointment")
    public Appointment createAppointment(@RequestBody AppointmentDto appointmentDto){
        return appointmentService.createAppointment(appointmentDto);
    }

    @PatchMapping("/updateDate/{appointmentId}")
    public AppointmentDto updateAppointment (@PathVariable Long appointmentId,
                                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime date
    ){
        return appointmentService.updateAppointment(appointmentId, date);
    }
}
