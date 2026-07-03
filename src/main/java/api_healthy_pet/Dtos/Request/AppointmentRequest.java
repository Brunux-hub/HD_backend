package api_healthy_pet.Dtos.Request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentRequest(@NotNull Long idPet, @NotNull Long idVeterinarian, @NotNull LocalDateTime date, String reason, String notes) {
}
