package com.example.groomingsalonapp.Controller;

import com.example.groomingsalonapp.DTO.HandlingDto;
import com.example.groomingsalonapp.Domain.Appointment;
import com.example.groomingsalonapp.Domain.Handling;
import com.example.groomingsalonapp.Service.HandlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/handling")
public class HandlingController {

    private final HandlingService handlingService;

    @PostMapping("/createHandling")
    public HandlingDto createHandling (@RequestBody Handling handling){
        return handlingService.createHandling(handling);
    }

    @GetMapping("/getTotalPrice/{clientId}")
        public Double getTotalPrice (@PathVariable Long clientId){
            return handlingService.getTotalPrice(clientId);
        }

    @GetMapping("/getAll")
    public List<Handling> getAllHandling(){
        return handlingService.getAllHandling();
    }

    @GetMapping("/getHandling/{handlingId}")
    public HandlingDto getHandling(@PathVariable Long handlingId){
        return  handlingService.getHandling(handlingId);
    }

    @DeleteMapping("/deleteHandling/{handlingId}")
    public void deleteHandling (@PathVariable Long handlingId){
        handlingService.deleteHandling(handlingId);
    }
}
