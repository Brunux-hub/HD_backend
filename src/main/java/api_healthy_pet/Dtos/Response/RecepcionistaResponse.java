package api_healthy_pet.Dtos.Response;

import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record RecepcionistaResponse(
        Long userId,
        UserResponse user,
        String nombre,
        String apellido,
        String telefono
) {
}
