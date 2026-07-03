package api_healthy_pet.Dtos.Response;

import api_healthy_pet.Enums.UserType;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UserResponse(
        Long idUser,
        String username,
        UserType type
) {
}
