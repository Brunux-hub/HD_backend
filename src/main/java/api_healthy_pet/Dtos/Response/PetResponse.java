package api_healthy_pet.Dtos.Response;

import api_healthy_pet.Enums.PetGender;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.util.Date;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PetResponse(
        Long idPet,
        OwnerResponse owner,
        String name,
        String species,
        String race,
        Date birthdate,
        PetGender petGender,
        String weight
) {
}
