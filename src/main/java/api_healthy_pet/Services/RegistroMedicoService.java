package api_healthy_pet.Services;

import api_healthy_pet.DTOs.request.RegistroMedicoRequest;
import api_healthy_pet.DTOs.response.RegistroMedicoResponse;
import api_healthy_pet.Entities.Cita;
import api_healthy_pet.Entities.RegistroMedico;
import api_healthy_pet.Enums.RolUsuario;
import api_healthy_pet.Exceptions.BadRequestException;
import api_healthy_pet.Exceptions.DuplicateResourceException;
import api_healthy_pet.Exceptions.ForbiddenException;
import api_healthy_pet.Exceptions.ResourceNotFoundException;
import api_healthy_pet.Mappers.RegistroMedicoMapper;
import api_healthy_pet.Repositories.RegistroMedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegistroMedicoService {

    private final RegistroMedicoRepository registroMedicoRepository;
    private final RegistroMedicoMapper registroMedicoMapper;
    private final CitaService citaService;
    private final CurrentUserService currentUserService;

    public List<RegistroMedicoResponse> findAll() {
        return registroMedicoRepository.findAll().stream()
                .filter(this::canRead)
                .map(registroMedicoMapper::toResponse)
                .toList();
    }

    public RegistroMedicoResponse findById(Long idRegistroMedico) {
        RegistroMedico registroMedico = getRegistroMedico(idRegistroMedico);
        validateReadAccess(registroMedico);
        return registroMedicoMapper.toResponse(registroMedico);
    }

    public RegistroMedicoResponse findByCita(Long idCita) {
        RegistroMedico registroMedico = registroMedicoRepository.findByCita_IdCita(idCita)
                .orElseThrow(() -> new ResourceNotFoundException("Registro medico no encontrado para la cita"));
        validateReadAccess(registroMedico);
        return registroMedicoMapper.toResponse(registroMedico);
    }

    @Transactional
    public RegistroMedicoResponse create(RegistroMedicoRequest request) {
        if (registroMedicoRepository.existsByCita_IdCita(request.getIdCita())) {
            throw new DuplicateResourceException("La cita ya tiene un registro medico asociado");
        }

        Cita cita = citaService.getCita(request.getIdCita());
        validateWriteAccess(cita);

        RegistroMedico registroMedico = new RegistroMedico();
        registroMedico.setCita(cita);
        registroMedico.setFecha(LocalDateTime.now());
        registroMedico.setDiagnostico(request.getDiagnostico());
        registroMedico.setMedicamentosRecetados(request.getMedicamentosRecetados());
        registroMedico.setObservaciones(request.getObservaciones());
        return registroMedicoMapper.toResponse(registroMedicoRepository.save(registroMedico));
    }

    @Transactional
    public RegistroMedicoResponse update(Long idRegistroMedico, RegistroMedicoRequest request) {
        RegistroMedico registroMedico = getRegistroMedico(idRegistroMedico);
        Cita cita = citaService.getCita(request.getIdCita());
        validateWriteAccess(cita);

        if (!registroMedico.getCita().getIdCita().equals(request.getIdCita())
                && registroMedicoRepository.existsByCita_IdCita(request.getIdCita())) {
            throw new DuplicateResourceException("La cita ya tiene un registro medico asociado");
        }

        registroMedico.setCita(cita);
        registroMedico.setDiagnostico(request.getDiagnostico());
        registroMedico.setMedicamentosRecetados(request.getMedicamentosRecetados());
        registroMedico.setObservaciones(request.getObservaciones());
        return registroMedicoMapper.toResponse(registroMedicoRepository.save(registroMedico));
    }

    public RegistroMedico getRegistroMedico(Long idRegistroMedico) {
        return registroMedicoRepository.findById(idRegistroMedico)
                .orElseThrow(() -> new ResourceNotFoundException("Registro medico no encontrado"));
    }

    public boolean canRead(RegistroMedico registroMedico) {
        RolUsuario rol = currentUserService.getCurrentRole();
        Long currentId = currentUserService.getCurrentUserId();
        Cita cita = registroMedico.getCita();

        if (rol == RolUsuario.ADMIN) {
            return true;
        }
        if (rol == RolUsuario.VETERINARIO) {
            return cita.getVeterinario().getIdUsuario().equals(currentId);
        }
        if (rol == RolUsuario.CLIENTE) {
            return cita.getMascota().getCliente().getIdUsuario().equals(currentId);
        }
        return false;
    }

    public void validateWriteAccess(RegistroMedico registroMedico) {
        validateWriteAccess(registroMedico.getCita());
    }

    private void validateReadAccess(RegistroMedico registroMedico) {
        if (!canRead(registroMedico)) {
            throw new ForbiddenException("No tiene permisos para acceder a este registro medico");
        }
    }

    private void validateWriteAccess(Cita cita) {
        RolUsuario rol = currentUserService.getCurrentRole();
        Long currentId = currentUserService.getCurrentUserId();

        if (rol == RolUsuario.ADMIN) {
            return;
        }
        if (rol == RolUsuario.VETERINARIO && cita.getVeterinario().getIdUsuario().equals(currentId)) {
            return;
        }

        throw new ForbiddenException("Solo el veterinario asignado o un administrador pueden modificar este registro");
    }
}
