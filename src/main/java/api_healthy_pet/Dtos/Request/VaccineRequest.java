package api_healthy_pet.Dtos.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VaccineRequest(@NotBlank String name, @NotBlank String description, @NotBlank String manufacturer, @NotNull Integer requiredDose) {
}
