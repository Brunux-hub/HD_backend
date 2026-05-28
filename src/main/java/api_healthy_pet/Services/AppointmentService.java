package api_healthy_pet.Services;

import api_healthy_pet.Dtos.Request.AppointmentRequest;
import api_healthy_pet.Dtos.Response.AppointmentResponse;
import api_healthy_pet.Entities.Appointment;
import api_healthy_pet.Exceptions.AppointmentException;
import api_healthy_pet.Mappers.AppointmentMapper;
import api_healthy_pet.Repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentMapper appointmentMapper;
    private final AppointmentRepository appointmentRepository;

    public AppointmentResponse create (AppointmentRequest request){
        return appointmentMapper.toResponse(appointmentRepository.save(appointmentMapper.toEntity(request)));
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

        return appointmentMapper.toResponse(appointmentRepository.save(appointment));
    }

    public void deleteById (Long idAppointment) {
        if (!appointmentRepository.existsById(idAppointment)){
            throw new AppointmentException("Cita no encontrada");
        }
        appointmentRepository.deleteById(idAppointment);
    }
}
