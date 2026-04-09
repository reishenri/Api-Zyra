package com.zyra.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DeviceRequestDTO(

        @NotBlank(message = "O nome do dispositivo é obrigatório")
        String name,

        @NotBlank(message = "O serialNumber é obrigatório")
        String serialNumber,

        String location,

        @NotNull(message = "O userId é obrigatório")
        Long userId

) {
}