package com.example.groomingsalonapp.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "pet")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pet_id", nullable = false)
    private Long petId;

    @Column(name = "pet_name")
    private String petName;

    @Column(name = "breed")
    private String breed;

    @Column(name = "age")
    private int age;

    @Column(name = "weight")
    private int weight;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;

}
