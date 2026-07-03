package api_healthy_pet.Dtos.Response;

import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record OwnerResponse(
        Long idOwner,
        String dni,
        String names,
        String lastNames,
        String email,
        String phoneNumber,
        String address
) {
}
