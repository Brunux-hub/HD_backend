package api_healthy_pet.Dtos.Response;

import api_healthy_pet.Enums.AppointmentStatus;

import java.time.LocalDateTime;

public record AppointmentResponse(Long idAppointment, ReceptionistResponse receptionist, PetResponse pet, VeterinarianResponse veterinarian, LocalDateTime date, Integer timeMinutes, String reason, String notes, AppointmentStatus status) {
}
