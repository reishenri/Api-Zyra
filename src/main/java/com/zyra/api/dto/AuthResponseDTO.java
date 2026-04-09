package com.zyra.api.dto;

public record AuthResponseDTO(
        Long id,
        String name,
        String email,
        String token
) {
}