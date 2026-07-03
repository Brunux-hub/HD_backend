package api_healthy_pet.Dtos.Response;

import api_healthy_pet.Enums.AppointmentStatus;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AppointmentResponse(
        Long idAppointment,
        ReceptionistResponse receptionist,
        PetResponse pet,
        VeterinarianResponse veterinarian,
        LocalDateTime date,
        Integer timeMinutes,
        String reason,
        String notes,
        AppointmentStatus status
) {
}
