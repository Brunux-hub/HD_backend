package api_healthy_pet.Dtos.Request;

import api_healthy_pet.Enums.EspecialidadVet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record VeterinarioRequest(
        @NotNull Long userId,
        @NotBlank String nombre,
        @NotBlank String apellido,
        String telefono,
        @NotNull List<EspecialidadVet> especialidades,
        @NotBlank String licencia
) {
}
