package api_healthy_pet.Dtos.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MedicalHistoryRequest(@NotNull Long idAppointment, @NotNull Long idService, @NotBlank String description, @NotNull LocalDateTime date) {
}
