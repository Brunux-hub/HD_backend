package api_healthy_pet.Dtos.Response;

import api_healthy_pet.Enums.AppointmentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record AppointmentResponse(
        @JsonProperty("idAppointment") Long idAppointment,
        ReceptionistResponse receptionist,
        PetResponse pet,
        VeterinarianResponse veterinarian,
        LocalDateTime date,
        @JsonProperty("timeMinutes") Integer timeMinutes,
        String reason,
        String notes,
        AppointmentStatus status
) {
}
