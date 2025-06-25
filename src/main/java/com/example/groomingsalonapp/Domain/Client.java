package com.example.groomingsalonapp.Domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "client")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToMany (mappedBy = "client", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Pet> pets = new ArrayList<>();

}
