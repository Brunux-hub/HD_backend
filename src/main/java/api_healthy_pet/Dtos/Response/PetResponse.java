package api_healthy_pet.Dtos.Response;

import api_healthy_pet.Enums.PetGender;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record PetResponse(
        @JsonProperty("idPet") Long idPet,
        OwnerResponse owner,
        String name,
        String species,
        String race,
        Date birthdate,
        @JsonProperty("petGender") PetGender petGender,
        String weight
) {
}
