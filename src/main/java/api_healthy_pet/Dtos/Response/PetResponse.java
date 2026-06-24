package api_healthy_pet.Dtos.Response;

import api_healthy_pet.Enums.PetGender;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record PetResponse(Long idPet, OwnerResponse owner, String name, String species, String race, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") Date birthdate, PetGender sex, String weight) {
}
