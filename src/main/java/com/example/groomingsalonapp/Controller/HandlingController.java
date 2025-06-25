package com.example.groomingsalonapp.Controller;

import com.example.groomingsalonapp.DTO.HandlingDto;
import com.example.groomingsalonapp.Domain.Handling;
import com.example.groomingsalonapp.Service.HandlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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


}
