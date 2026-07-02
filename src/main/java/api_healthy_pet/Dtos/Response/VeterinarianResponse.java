package api_healthy_pet.Dtos.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record VeterinarianResponse(
        @JsonProperty("idVeterinarian") Long idVeterinarian,
        @JsonProperty("userResponse") UserResponse userResponse,
        String names,
        @JsonProperty("lastNames") String lastNames,
        @JsonProperty("numberLicense") String numberLicense,
        String specialty,
        String email,
        @JsonProperty("phoneNumber") String phoneNumber
) {
}
