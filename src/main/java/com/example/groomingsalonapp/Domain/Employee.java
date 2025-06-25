package com.example.groomingsalonapp.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "post")
    @Enumerated(EnumType.STRING)
    private Post post;

    @Column(name = "experience")
    private int experienceYears;

    @Column(name = "work_start")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime workStart;

    @Column(name = "work_end")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime workEnd;

    @Column(name = "work_day")
    private String workDay;

}
