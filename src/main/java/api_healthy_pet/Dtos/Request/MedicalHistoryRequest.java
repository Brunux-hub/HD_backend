package api_healthy_pet.Dtos.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MedicalHistoryRequest(@NotNull Long idAppointment, @NotNull Long idService, @NotNull LocalDateTime date, @NotBlank String weight, @NotBlank String diagnosis, @NotBlank String treatment) {
}
