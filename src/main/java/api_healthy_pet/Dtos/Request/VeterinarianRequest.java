package api_healthy_pet.Dtos.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VeterinarianRequest(@NotNull Long idUser, @NotBlank String names, @NotBlank String lastNames, @NotBlank String numberLicense, @NotBlank String specialty, @NotBlank String email, String phoneNumber) {
}
