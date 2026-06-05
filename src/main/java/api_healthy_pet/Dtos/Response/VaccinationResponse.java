package api_healthy_pet.Dtos.Response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record VaccinationResponse(Long idVaccination, MedicalHistoryResponse medicalHistory, VaccineResponse vaccine, LocalDate applicationDate, LocalDate nextApplicationDate, String observation) {
}
