package api_healthy_pet.Dtos.Response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record MedicalHistoryResponse(Long idMedicalHistory, AppointmentResponse appointment, ServiceResponse services, String description, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime date) {
}
