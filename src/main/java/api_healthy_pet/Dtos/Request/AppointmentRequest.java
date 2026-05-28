package api_healthy_pet.Dtos.Request;

import api_healthy_pet.Enums.AppointmentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentRequest(@NotNull Long idReceptionist, @NotNull Long idPet, @NotNull Long idVeterinarian, @NotNull LocalDateTime date, @NotNull Integer timeMinutes, @NotBlank String reason, @NotBlank String notes, @NotNull AppointmentStatus status) {
}
