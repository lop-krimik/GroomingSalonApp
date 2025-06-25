package com.example.groomingsalonapp.Controller;

import com.example.groomingsalonapp.DTO.PetDto;
import com.example.groomingsalonapp.Domain.Pet;
import com.example.groomingsalonapp.Service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping("/createPet/{clientId}")
    public PetDto createPet(@PathVariable Long clientId, @RequestBody Pet pet){
       return petService.createPetWithClient(pet, clientId);
    }
}
