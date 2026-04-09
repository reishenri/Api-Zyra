package com.zyra.api.dto;

public record IrrigationDecisionResponseDTO(
        Boolean shouldIrrigate,
        Integer durationSeconds,
        String reason
) {
}