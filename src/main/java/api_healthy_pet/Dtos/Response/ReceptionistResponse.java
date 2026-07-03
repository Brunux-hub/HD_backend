package api_healthy_pet.Dtos.Response;

import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ReceptionistResponse(
        Long idReceptionist,
        UserResponse user,
        String names,
        String lastNames,
        String email,
        String phoneNumber
) {
}
