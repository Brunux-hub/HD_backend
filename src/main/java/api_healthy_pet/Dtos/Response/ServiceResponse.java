package api_healthy_pet.Dtos.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ServiceResponse(
        @JsonProperty("idService") Long idService,
        String name,
        String description,
        Integer price
) {
}
