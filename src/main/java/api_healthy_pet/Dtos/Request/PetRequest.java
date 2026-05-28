package api_healthy_pet.Dtos.Request;

import api_healthy_pet.Enums.PetGender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record PetRequest(@NotNull Long idOwner, @NotBlank  String name, @NotBlank String species, @NotBlank String race, @NotNull Date birthdate, @NotNull PetGender sex, @NotBlank String weight) {
}
