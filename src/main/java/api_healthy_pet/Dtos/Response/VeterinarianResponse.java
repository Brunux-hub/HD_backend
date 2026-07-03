package api_healthy_pet.Dtos.Response;

import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record VeterinarianResponse(
        Long idVeterinarian,
        UserResponse userResponse,
        String names,
        String lastNames,
        String numberLicense,
        String specialty,
        String email,
        String phoneNumber
) {
}
