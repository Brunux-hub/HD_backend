package api_healthy_pet.Dtos.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReceptionistRequest(@NotNull Long idUser, @NotBlank String names, @NotBlank String lastNames, @NotBlank String email, @NotBlank String phoneNumber) {
}
