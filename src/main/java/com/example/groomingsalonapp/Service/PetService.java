package com.example.groomingsalonapp.Service;

import com.example.groomingsalonapp.DTO.PetDto;
import com.example.groomingsalonapp.Domain.Client;
import com.example.groomingsalonapp.Domain.Pet;
import com.example.groomingsalonapp.ExceptiionHandler.ClientException.ClientNotFoundException;
import com.example.groomingsalonapp.Repository.ClientRepository;
import com.example.groomingsalonapp.Repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;
    private final ClientRepository clientRepository;


    public PetDto createPetWithClient(Pet pet, Long clientId){
      Client client = clientRepository.findById(clientId)
              .orElseThrow(()-> new ClientNotFoundException(String.format("Client with id: %d was not found", clientId)));
        pet.setClient(client);
        petRepository.save(pet);
        return PetDto.fromPetEntity(pet);
    }


}
