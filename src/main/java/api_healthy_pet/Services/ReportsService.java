package api_healthy_pet.Services;

import api_healthy_pet.Dtos.Response.AppointmentResponse;
import api_healthy_pet.Dtos.Response.ServiceResponse;
import api_healthy_pet.Enums.AppointmentStatus;
import api_healthy_pet.Mappers.AppointmentMapper;
import api_healthy_pet.Mappers.PetMapper;
import api_healthy_pet.Mappers.VeterinarianMapper;
import api_healthy_pet.Repositories.AppointmentRepository;
import api_healthy_pet.Repositories.PetRepository;
import api_healthy_pet.Mappers.ServiceMapper;
import api_healthy_pet.Repositories.ServicesRepository;
import api_healthy_pet.Repositories.VeterinarianRepository;
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
    private final VeterinarianRepository veterinarianRepository;
    // Mappers
    private final ServiceMapper serviceMapper;
    private final AppointmentMapper appointmentMapper;
    private final PetMapper petMapper;
    private final VeterinarianMapper veterinarianMapper;

    //Filtrar Citas por Status
    public List<AppointmentResponse> getAppointmentsByStatus(AppointmentStatus status){
        return appointmentRepository.findByStatus(status)
                .stream()
                .map(appointmentMapper::toResponse)
                .toList();
    }

}
