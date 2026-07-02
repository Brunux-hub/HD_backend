package api_healthy_pet.Dtos.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import api_healthy_pet.Enums.UserType;

public record UserResponse(
        @JsonProperty("idUser") Long idUser,
        String username,
        UserType type
) {
}
