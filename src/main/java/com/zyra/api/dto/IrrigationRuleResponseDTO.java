package com.zyra.api.dto;

public record IrrigationRuleResponseDTO(
        Long id,
        Long deviceId,
        Double minSoilMoisture,
        Double maxTemperature,
        Integer irrigationDurationSeconds,
        Boolean automaticMode,
        Integer cooldownMinutes
) {
}