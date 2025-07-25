package com.example.groomingsalonapp.Service;

import com.example.groomingsalonapp.DTO.HandlingDto;
import com.example.groomingsalonapp.Domain.Client;
import com.example.groomingsalonapp.Domain.Handling;
import com.example.groomingsalonapp.Domain.HandlingName;
import com.example.groomingsalonapp.ExceptiionHandler.HandlingException.HandlingNotFoundException;
import com.example.groomingsalonapp.Repository.AppointmentRepository;
import com.example.groomingsalonapp.Repository.HandlingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HandlingService {

    private final HandlingRepository handlingRepository;
    private final AppointmentService appointmentService;

    public Integer setPrice (Handling handling){
        HandlingName handlingName = handling.getHandlingName();
        int cost = Handling.getPrice().get(handlingName);
        return cost;
    }

    public Double getTotalPrice(Long clientId){
        double price = 0;
        if(appointmentService.countAppointment(clientId) > 10){
           price = handlingRepository.calculateTotalRevenueWithDiscount(0.10);
        }
        else{
            price = handlingRepository.calculateTotalRevenueWithDiscount(0);
        }
        return price;
    }

    public Duration setDuration(Handling handling){
        HandlingName handlingName = handling.getHandlingName();
        Duration duration = Handling.getDurationHandling().get(handlingName);
        return duration;
    }

    public HandlingDto createHandling(Handling handling){
        handling.setTotalCost(setPrice(handling));
        handling.setDuration(setDuration(handling));
        handlingRepository.save(handling);
        return HandlingDto.fromEntityHandling(handling);
    }
    public List<Handling> getAllHandling(){
        return handlingRepository.findAll();
    }

    public HandlingDto getHandling(Long handlingId){
        Handling handling = handlingRepository.findById(handlingId)
                .orElseThrow(()-> new HandlingNotFoundException(handlingId));
        return HandlingDto.fromEntityHandling(handling);
    }

    @Transactional
    public void deleteHandling(Long handlingId){
        Handling handling = handlingRepository.findById(handlingId)
                .orElseThrow(()-> new HandlingNotFoundException(handlingId));
        handlingRepository.delete(handling);
    }
}
