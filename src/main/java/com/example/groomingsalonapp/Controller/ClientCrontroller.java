package com.example.groomingsalonapp.Controller;

import com.example.groomingsalonapp.DTO.ClientDto;
import com.example.groomingsalonapp.Domain.Appointment;
import com.example.groomingsalonapp.Domain.Client;
import com.example.groomingsalonapp.Domain.Pet;
import com.example.groomingsalonapp.Service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientCrontroller {
    private final ClientService clientService;

    @PostMapping("/createClient")
    public ClientDto createClient(@RequestBody Client client){
        return clientService.createClient(client);
    }

    @GetMapping("/getClientByPhone")
    public ClientDto findClientByPhone (@RequestParam String phone){
        return clientService.findClientByPhone(phone);
    }

    @DeleteMapping("/deleteClient/{clientId}")
    public void deleteClient (@PathVariable Long clientId){
        clientService.deleteClient(clientId);
    }

    @PatchMapping("/updateClient/{clientId}")
    public ClientDto updateClient (@RequestBody Client client, @PathVariable Long clientId){
        return  clientService.updateClient(client, clientId);
    }

    @GetMapping("/getAll")
    public List<Client> getAllAppointment(){
        return clientService.getAllClient();
    }

    @GetMapping("/getClient/{clientId}")
    public ClientDto getClient(@PathVariable Long clientId){
        return  clientService.getClient(clientId);
    }
}
