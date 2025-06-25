package com.example.groomingsalonapp.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AppointmentDto {
    private Long employeeId;
    private Long clientId;
    private Long petId;
    private Long handlingId;
    private LocalDateTime dateTime;
}
