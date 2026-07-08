package api_healthy_pet.Dtos.Response;

import api_healthy_pet.Enums.EspecialidadVet;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record VeterinarioResponse(
        Long userId,
        UserResponse userResponse,
        String nombre,
        String apellido,
        String telefono,
        List<EspecialidadVet> especialidades,
        String licencia
) {
}
