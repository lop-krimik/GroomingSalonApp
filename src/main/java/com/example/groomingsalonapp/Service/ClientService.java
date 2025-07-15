package com.example.groomingsalonapp.Service;

import com.example.groomingsalonapp.DTO.ClientDto;
import com.example.groomingsalonapp.Domain.Appointment;
import com.example.groomingsalonapp.Domain.Client;
import com.example.groomingsalonapp.Domain.Pet;
import com.example.groomingsalonapp.ExceptiionHandler.ClientException.ClientAlreadyExistsException;
import com.example.groomingsalonapp.ExceptiionHandler.ClientException.ClientNotFoundException;
import com.example.groomingsalonapp.Repository.AppointmentRepository;
import com.example.groomingsalonapp.Repository.ClientRepository;
import com.example.groomingsalonapp.Repository.PetRepository;
import jakarta.transaction.Transactional;
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
                .orElseThrow(()-> new ClientNotFoundException(phone)));
    }

    @Transactional
    public void deleteClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(()-> new ClientNotFoundException(clientId));
        List<Appointment> appointments = appointmentRepository.findByClient_ClientId(clientId);
        appointmentRepository.deleteAll(appointments);
        clientRepository.delete(client);
    }

    public ClientDto updateClient(Client newClient, Long clientId){
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(
                        ()-> new ClientNotFoundException(clientId)
                );

        existingClient.setClientName(newClient.getClientName());
        existingClient.setPhone(newClient.getPhone());
        existingClient.setEmail(newClient.getEmail());

        Client updateClient = clientRepository.save(existingClient);
        return ClientDto.fromClientEntity(updateClient);

    }
    public List<Client> getAllClient(){
        return clientRepository.findAll();
    }
    public ClientDto getClient(Long clientId){
        Client client = clientRepository.findById(clientId).orElseThrow(()-> new ClientNotFoundException(clientId));
        return ClientDto.fromClientEntity(client);
    }

}
