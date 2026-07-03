package api_healthy_pet.Dtos.Request;

import jakarta.validation.constraints.NotBlank;

public record OwnerRequest(@NotBlank String dni, @NotBlank String names, @NotBlank String lastNames, @NotBlank String email, @NotBlank String phoneNumber, @NotBlank String address) {
}
