package com.zyra.api.dto;

import jakarta.validation.constraints.NotNull;

public record IrrigationCommandRequestDTO(

        @NotNull(message = "O deviceId é obrigatório")
        Long deviceId,

        @NotNull(message = "A duração da irrigação é obrigatória")
        Integer durationSeconds

) {
}