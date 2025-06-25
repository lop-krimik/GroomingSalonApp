package com.example.groomingsalonapp.DTO;

import com.example.groomingsalonapp.Domain.Pet;
import jakarta.persistence.Column;

public record PetDto(
        String petName,
        String breed,
        int age,
        int weight,
        String description,
        Long clientId
) {
        public static PetDto fromPetEntity(Pet pet){

                return new PetDto(
                        pet.getPetName(),
                        pet.getBreed(),
                        pet.getAge(),
                        pet.getWeight(),
                        pet.getDescription(),
                        pet.getClient().getClientId()
                        );
        }
}
