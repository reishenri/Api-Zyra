package com.zyra.api.dto;

import jakarta.validation.constraints.NotNull;

public record SensorDataRequestDTO(

        @NotNull(message = "O deviceId é obrigatório")
        Long deviceId,

        @NotNull(message = "A temperatura é obrigatória")
        Double temperature,

        @NotNull(message = "A umidade do ar é obrigatória")
        Double airHumidity,

        @NotNull(message = "A umidade do solo é obrigatória")
        Double soilMoisture,

        Double waterLevel

) {
}