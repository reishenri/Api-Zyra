package com.zyra.api.dto;

import java.time.LocalDateTime;

public record IrrigationLogResponseDTO(
        Long id,
        Long deviceId,
        Integer durationSeconds,
        String mode,
        LocalDateTime createdAt
) {
}