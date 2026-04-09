package com.zyra.api.dto;

import jakarta.validation.constraints.NotNull;

public record IrrigationRuleRequestDTO(

        @NotNull(message = "O deviceId é obrigatório")
        Long deviceId,

        @NotNull(message = "A umidade mínima do solo é obrigatória")
        Double minSoilMoisture,

        @NotNull(message = "A temperatura máxima é obrigatória")
        Double maxTemperature,

        @NotNull(message = "A duração da irrigação é obrigatória")
        Integer irrigationDurationSeconds,

        @NotNull(message = "O modo automático é obrigatório")
        Boolean automaticMode,

        @NotNull(message = "O cooldown é obrigatório")
        Integer cooldownMinutes

) {
}