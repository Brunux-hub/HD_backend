package api_healthy_pet.Dtos.Response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VaccineResponse(Long idVaccine, String name, String description, String manufacturer, Integer requiredDose) {
}
