package api_healthy_pet.Services;

import api_healthy_pet.DTOs.request.TratamientoRequest;
import api_healthy_pet.DTOs.response.TratamientoResponse;
import api_healthy_pet.Entities.RegistroMedico;
import api_healthy_pet.Entities.Tratamiento;
import api_healthy_pet.Exceptions.ForbiddenException;
import api_healthy_pet.Exceptions.ResourceNotFoundException;
import api_healthy_pet.Mappers.TratamientoMapper;
import api_healthy_pet.Repositories.TratamientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TratamientoService {

    private final TratamientoRepository tratamientoRepository;
    private final TratamientoMapper tratamientoMapper;
    private final RegistroMedicoService registroMedicoService;

    public List<TratamientoResponse> findAll() {
        return tratamientoRepository.findAll().stream()
                .filter(tratamiento -> registroMedicoService.canRead(tratamiento.getRegistroMedico()))
                .map(tratamientoMapper::toResponse)
                .toList();
    }

    public TratamientoResponse findById(Long idTratamiento) {
        Tratamiento tratamiento = getTratamiento(idTratamiento);
        if (!registroMedicoService.canRead(tratamiento.getRegistroMedico())) {
            throw new ForbiddenException("No tiene permisos para acceder a este tratamiento");
        }
        return tratamientoMapper.toResponse(tratamiento);
    }

    @Transactional
    public TratamientoResponse create(TratamientoRequest request) {
        RegistroMedico registroMedico = registroMedicoService.getRegistroMedico(request.getIdRegistroMedico());
        registroMedicoService.validateWriteAccess(registroMedico);

        Tratamiento tratamiento = new Tratamiento();
        applyRequest(tratamiento, request, registroMedico);
        return tratamientoMapper.toResponse(tratamientoRepository.save(tratamiento));
    }

    @Transactional
    public TratamientoResponse update(Long idTratamiento, TratamientoRequest request) {
        Tratamiento tratamiento = getTratamiento(idTratamiento);
        RegistroMedico registroMedico = registroMedicoService.getRegistroMedico(request.getIdRegistroMedico());
        registroMedicoService.validateWriteAccess(registroMedico);

        applyRequest(tratamiento, request, registroMedico);
        return tratamientoMapper.toResponse(tratamientoRepository.save(tratamiento));
    }

    public List<TratamientoResponse> findByRegistroMedico(Long idRegistroMedico) {
        RegistroMedico registroMedico = registroMedicoService.getRegistroMedico(idRegistroMedico);
        if (!registroMedicoService.canRead(registroMedico)) {
            throw new ForbiddenException("No tiene permisos para acceder a estos tratamientos");
        }
        return tratamientoRepository.findByRegistroMedico_IdRegistroMedico(idRegistroMedico).stream()
                .map(tratamientoMapper::toResponse)
                .toList();
    }

    public Tratamiento getTratamiento(Long idTratamiento) {
        return tratamientoRepository.findById(idTratamiento)
                .orElseThrow(() -> new ResourceNotFoundException("Tratamiento no encontrado"));
    }

    private void applyRequest(Tratamiento tratamiento, TratamientoRequest request, RegistroMedico registroMedico) {
        tratamiento.setRegistroMedico(registroMedico);
        tratamiento.setMedicamento(request.getMedicamento());
        tratamiento.setDosis(request.getDosis());
        tratamiento.setFrecuencia(request.getFrecuencia());
        tratamiento.setDuracion(request.getDuracion());
        tratamiento.setIndicaciones(request.getIndicaciones());
    }
}
