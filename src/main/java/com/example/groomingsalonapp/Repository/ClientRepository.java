package com.example.groomingsalonapp.Repository;


import com.example.groomingsalonapp.Domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Boolean existsByPhone(String phone);
    Optional<Client> findClientByPhone(String phone);
}
