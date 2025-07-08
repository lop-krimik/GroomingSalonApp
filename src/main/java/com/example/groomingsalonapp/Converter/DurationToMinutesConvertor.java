package com.example.groomingsalonapp.Converter;

import jakarta.persistence.AttributeConverter;

import java.time.Duration;

public class DurationToMinutesConvertor implements AttributeConverter<Duration, Long> {
    @Override
    public Long convertToDatabaseColumn(Duration duration) {
        return duration.toMinutes();
    }

    @Override
    public Duration convertToEntityAttribute(Long minutes) {
        return Duration.ofMinutes(minutes);
    }
}
