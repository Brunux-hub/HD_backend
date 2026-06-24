package api_healthy_pet.Dtos.Response;

import api_healthy_pet.Enums.PetGender;
import java.util.Date;

public record PetResponse(Long idPet, OwnerResponse owner, String name, String species, String race, Date birthdate, PetGender sex, String weight) {
}
