package com.example.groomingsalonapp.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "appointment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "appointment_id", nullable = false)
    private Long appointmentId;

        @ManyToOne
        @JoinColumn(name = "client_id")
        private Client client;

        @ManyToOne
        @JoinColumn(name = "pet_id")
        private Pet pet;

        @ManyToOne
        @JoinColumn(name = "handling_id")
        private Handling handling;

        @ManyToOne
        @JoinColumn(name = "employee_id")
        private Employee employee;

        @Column(name = "date_time")
        private LocalDateTime dateTime;

    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;

}
