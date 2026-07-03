package api_healthy_pet.Services;

import api_healthy_pet.Dtos.Request.AppointmentRequest;
import api_healthy_pet.Dtos.Response.AppointmentResponse;
import api_healthy_pet.Entities.Appointment;
import api_healthy_pet.Entities.Receptionist;
import api_healthy_pet.Exceptions.AppointmentException;
import api_healthy_pet.Exceptions.ReceptionistException;
import api_healthy_pet.Mappers.AppointmentMapper;
import api_healthy_pet.Repositories.AppointmentRepository;
import api_healthy_pet.Repositories.ReceptionistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentMapper appointmentMapper;
    private final AppointmentRepository appointmentRepository;
    private final ReceptionistRepository receptionistRepository;

    @Transactional
    public AppointmentResponse create (AppointmentRequest request){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Appointment appointment = appointmentMapper.toEntity(request);

        receptionistRepository.findByUser_Username(username)
                .ifPresentOrElse(
                        appointment::setReceptionist,
                        () -> {
                            boolean isAdmin = auth.getAuthorities().stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
                            if (!isAdmin) {
                                throw new ReceptionistException("Solo un recepcionista puede registrar citas");
                            }
                        }
                );

        return appointmentMapper.toResponse(appointmentRepository.save(appointment));
    }

    public AppointmentResponse findById (Long idAppointment){
        return appointmentRepository.findById(idAppointment)
                .map(appointmentMapper::toResponse)
                .orElseThrow(() -> new AppointmentException("Cita no encontrada"));
    }

    public List<AppointmentResponse> findAll(){
        return appointmentRepository.findAll()
                .stream().map(appointmentMapper::toResponse).toList();
    }

    public AppointmentResponse updateById (Long idAppointment, AppointmentRequest request){
        Appointment appointment = appointmentRepository.findById(idAppointment)
                .orElseThrow(() -> new AppointmentException("Cita no encontrada"));

        appointmentMapper.updateEntityFromRequest(request, appointment);

        Appointment updatedAppointment = appointmentRepository.save(appointment);

        return appointmentRepository.findById(updatedAppointment.getIdAppointment())
                .map(appointmentMapper::toResponse)
                .orElseThrow(() -> new AppointmentException("Cita no encontrada"));
    }

    public void deleteById (Long idAppointment) {
        if (!appointmentRepository.existsById(idAppointment)){
            throw new AppointmentException("Cita no encontrada");
        }
        appointmentRepository.deleteById(idAppointment);
    }
}
