package api_healthy_pet.Dtos.Request;

import api_healthy_pet.Enums.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
    @NotBlank String username,
    @NotBlank String password,
    @NotNull UserType type) {
}
