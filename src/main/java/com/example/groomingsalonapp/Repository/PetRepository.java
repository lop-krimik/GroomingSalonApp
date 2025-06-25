package com.example.groomingsalonapp.Repository;

import com.example.groomingsalonapp.Domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet,Long> {
}
