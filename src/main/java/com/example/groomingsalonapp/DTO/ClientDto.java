package com.example.groomingsalonapp.DTO;

import com.example.groomingsalonapp.Domain.Client;
import com.example.groomingsalonapp.Domain.Pet;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.util.List;

public record ClientDto(String clientName,
                        String phone,
                        List<PetDto> pets
) {
    public static ClientDto fromClientEntity(Client client){

        return new ClientDto(
                    client.getClientName(),
                    client.getPhone(),
                    client.getPets().stream()
                            .map(PetDto::fromPetEntity)
                            .toList()
        );
    }

}
