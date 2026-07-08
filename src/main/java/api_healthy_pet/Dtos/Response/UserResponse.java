package api_healthy_pet.Dtos.Response;

import api_healthy_pet.Enums.Role;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UserResponse(
        Long id,
        String email,
        Role role
) {
}
