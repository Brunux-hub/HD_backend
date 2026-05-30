package api_healthy_pet.Dtos.Response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MedicalHistoryResponse(Long idMedicalHistory, AppointmentResponse appointment, ServiceResponse services, String description, LocalDateTime date) {
}
