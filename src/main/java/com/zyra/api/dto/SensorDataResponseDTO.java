package com.zyra.api.dto;

import java.time.LocalDateTime;

public record SensorDataResponseDTO(
        Long id,
        Long deviceId,
        Double temperature,
        Double airHumidity,
        Double soilMoisture,
        Double waterLevel,
        LocalDateTime createdAt
) {
}