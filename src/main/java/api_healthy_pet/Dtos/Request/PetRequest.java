package api_healthy_pet.Dtos.Request;

import api_healthy_pet.Enums.PetGender;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record PetRequest(
        @JsonProperty("idOwner")
        @JsonAlias({"ownerId", "id_owner"})
        @NotNull Long idOwner,
        @NotBlank String name,
        @NotBlank String species,
        @NotBlank String race,
        @NotNull Date birthdate,
        @NotNull PetGender sex
) {
}
