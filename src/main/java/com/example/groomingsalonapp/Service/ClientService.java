package com.example.groomingsalonapp.Service;

import com.example.groomingsalonapp.DTO.ClientDto;
import com.example.groomingsalonapp.Domain.Client;
import com.example.groomingsalonapp.Domain.Pet;
import com.example.groomingsalonapp.ExceptiionHandler.ClientException.ClientAlreadyExistsException;
import com.example.groomingsalonapp.ExceptiionHandler.ClientException.ClientNotFoundException;
import com.example.groomingsalonapp.Repository.AppointmentRepository;
import com.example.groomingsalonapp.Repository.ClientRepository;
import com.example.groomingsalonapp.Repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final AppointmentRepository appointmentRepository;

    public ClientDto createClient(Client client){
            if(clientRepository.existsByPhone(client.getPhone())){
                throw new ClientAlreadyExistsException(client.getClientName(), client.getPhone());
            }
            else{
                clientRepository.save(client);
              return  ClientDto.fromClientEntity(client);
            }
    }

    public ClientDto findClientByPhone (String phone)
    {
        return ClientDto.fromClientEntity(clientRepository.findClientByPhone(phone)
                .orElseThrow(()-> new ClientNotFoundException("Client with phone " + phone + " was not found")));
    }

}
