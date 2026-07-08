package api_healthy_pet.Dtos.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RecepcionistaRequest(
        @NotNull Long userId,
        @NotBlank String nombre,
        @NotBlank String apellido,
        String telefono
) {
}
