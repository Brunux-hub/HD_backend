package api_healthy_pet.Dtos.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdminRequest(
        @NotNull Long userId,
        @NotBlank String nombre,
        @NotBlank String apellido
) {
}
