package com.zyra.api.dto;

import java.time.LocalDateTime;

public record AlertResponseDTO(
        Long id,
        String message,
        String type,
        Boolean active,
        Long deviceId,
        LocalDateTime createdAt
) {
}