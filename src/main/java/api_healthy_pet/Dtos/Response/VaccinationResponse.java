package api_healthy_pet.Dtos.Response;

import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record VaccinationResponse(
        Long idVaccination,
        MedicalHistoryResponse medicalHistory,
        VaccineResponse vaccine,
        LocalDate applicationDate,
        LocalDate nextApplicationDate,
        String observation
) {
}
