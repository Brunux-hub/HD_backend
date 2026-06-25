package api_healthy_pet.Dtos.Response;

import java.time.LocalDate;

public record VaccinationResponse(Long idVaccination, MedicalHistoryResponse medicalHistory, VaccineResponse vaccine, LocalDate applicationDate, LocalDate nextApplicationDate, String observation) {
}
