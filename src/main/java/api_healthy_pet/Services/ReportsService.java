package api_healthy_pet.Services;

import api_healthy_pet.Dtos.Response.AppointmentResponse;
import api_healthy_pet.Dtos.Response.MedicalHistoryResponse;
import api_healthy_pet.Dtos.Response.ServiceResponse;
import api_healthy_pet.Enums.AppointmentStatus;
import api_healthy_pet.Mappers.*;
import api_healthy_pet.Repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // Genera Automaticamente el Constructor (Lombok)
public class ReportsService {
    // Repositorios
    private final ServicesRepository servicesRepository;
    private final AppointmentRepository appointmentRepository;
    private final PetRepository petRepository;
    private final VeterinarioRepository veterinarianRepository;
    private final MedicalHistoryRepository medicalHistoryRepository;
    // Mappers
    private final ServiceMapper serviceMapper;
    private final AppointmentMapper appointmentMapper;
    private final PetMapper petMapper;
    private final VeterinarioMapper veterinarianMapper;
    private final MedicalHistoryMapper medicalHistoryMapper;

    //Filtrar Citas por Status
    public List<AppointmentResponse> getAppointmentsByStatus(AppointmentStatus status){
        return appointmentRepository.findByStatus(status)
                .stream()
                .map(appointmentMapper::toResponse)
                .toList();
    }

    // Obtener Servicios Completados
    public List<MedicalHistoryResponse> getCompletedServices() {
        return medicalHistoryRepository
                .findByAppointmentStatus(AppointmentStatus.CLOSED)
                .stream()
                .map(medicalHistoryMapper::toResponse)
                .toList();
    }

}
