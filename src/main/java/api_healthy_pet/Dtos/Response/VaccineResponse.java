package api_healthy_pet.Dtos.Response;

import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record VaccineResponse(
        Long idVaccine,
        String name,
        String description,
        String manufacturer,
        Integer requiredDose
) {
}
