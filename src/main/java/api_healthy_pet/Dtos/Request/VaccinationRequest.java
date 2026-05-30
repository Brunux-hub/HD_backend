package api_healthy_pet.Dtos.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record VaccinationRequest(@NotNull Long idMedicalHistory, @NotNull Long idVaccine, @NotNull LocalDate applicationDate, @NotNull LocalDate nextApplicationDate, @NotBlank String observation) {
}
