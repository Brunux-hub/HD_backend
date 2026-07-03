package api_healthy_pet.Dtos.Response;

import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record MedicalHistoryResponse(
        Long idMedicalHistory,
        AppointmentResponse appointment,
        ServiceResponse services,
        LocalDateTime date,
        String weight,
        String diagnosis,
        String treatment
) {
}
