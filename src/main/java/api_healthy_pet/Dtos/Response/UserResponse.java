package api_healthy_pet.Dtos.Response;

import api_healthy_pet.Enums.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserResponse(Long idUser, String username, UserType type) {
}
