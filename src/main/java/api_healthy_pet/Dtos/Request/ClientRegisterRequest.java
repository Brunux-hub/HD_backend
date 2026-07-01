package api_healthy_pet.Dtos.Request;

import jakarta.validation.constraints.NotBlank;

// Registro público de un cliente: crea el User (login) y su Owner (ficha).
public record ClientRegisterRequest(
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String names,
        @NotBlank String lastNames,
        @NotBlank String email,
        @NotBlank String phoneNumber,
        @NotBlank String address
) {
}
