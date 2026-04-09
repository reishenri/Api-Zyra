package com.zyra.api.dto;

public record DeviceResponseDTO(
        Long id,
        String name,
        String serialNumber,
        String location,
        Long userId
) {
}