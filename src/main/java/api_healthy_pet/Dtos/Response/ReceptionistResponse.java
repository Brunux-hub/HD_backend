package api_healthy_pet.Dtos.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReceptionistResponse(
        @JsonProperty("idReceptionist") Long idReceptionist,
        UserResponse user,
        String names,
        @JsonProperty("lastNames") String lastNames,
        String email,
        @JsonProperty("phoneNumber") String phoneNumber
) {
}
