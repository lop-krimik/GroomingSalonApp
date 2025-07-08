package com.example.groomingsalonapp.Domain;

import com.example.groomingsalonapp.Converter.DurationToMinutesConvertor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity (name = "handling")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "handling")
public class Handling {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "handling_id", nullable = false)
    private Long handlingId;

    @Enumerated(EnumType.STRING)
    @Column(name = "handling_name")
    private HandlingName handlingName;

    @Column(name = "duration")
    @Convert(converter = DurationToMinutesConvertor.class)
    private Duration duration;


    @Column(name = "total_cost")
    private int totalCost;

    private static final Map<HandlingName,Integer> price = Map.of(
            HandlingName.EAR_CLINNING, 300,
            HandlingName.GROOMING, 500,
            HandlingName.NAIL_TRIMMING, 750,
            HandlingName.WASHING, 250
    );

    private static final Map<HandlingName,Duration> durationHandling = Map.of(
            HandlingName.EAR_CLINNING, Duration.ofMinutes(10),
            HandlingName.GROOMING, Duration.ofMinutes(30),
            HandlingName.NAIL_TRIMMING, Duration.ofMinutes(25),
            HandlingName.WASHING, Duration.ofMinutes(5)
    );

    public static Map<HandlingName, Integer> getPrice() {
        return price;
    }

    public static Map<HandlingName, Duration> getDurationHandling() {
        return durationHandling;
    }
}
