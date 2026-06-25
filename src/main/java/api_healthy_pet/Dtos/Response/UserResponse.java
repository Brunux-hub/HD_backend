package api_healthy_pet.Dtos.Response;

import api_healthy_pet.Enums.UserType;

public record UserResponse(Long idUser, String username, UserType type) {
}
