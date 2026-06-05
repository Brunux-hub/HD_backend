package api_healthy_pet.Dtos.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ServiceRequest(@NotBlank String name, @NotBlank String description, @NotNull Integer price) {
}
