package com.example.groomingsalonapp.DTO;

import com.example.groomingsalonapp.Domain.Handling;
import com.example.groomingsalonapp.Domain.HandlingName;

import java.time.Duration;

public record HandlingDto(
        HandlingName handlingName,
        Duration duration,
        int cost
) {
    public static HandlingDto fromEntityHandling (Handling handling){
       return new HandlingDto(
               handling.getHandlingName(),
               handling.getDuration(),
               handling.getTotalCost()
       );
    }
}
