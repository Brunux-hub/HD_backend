package api_healthy_pet.Dtos.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OwnerResponse(
        @JsonProperty("idOwner") Long idOwner,
        String names,
        @JsonProperty("lastNames") String lastNames,
        String email,
        @JsonProperty("phoneNumber") String phoneNumber,
        String address
) {
}
