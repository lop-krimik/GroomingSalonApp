package com.example.groomingsalonapp.Controller;

import com.example.groomingsalonapp.DTO.ClientDto;
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
}
