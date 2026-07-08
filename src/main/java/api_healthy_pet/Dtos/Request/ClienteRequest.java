package api_healthy_pet.Dtos.Request;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequest(
        @NotBlank String nombre,
        @NotBlank String apellido,
        String telefono,
        String direccion
) {
}
