package api_healthy_pet.Services;

import api_healthy_pet.DTOs.request.CitaEstadoRequest;
import api_healthy_pet.DTOs.request.CitaRequest;
import api_healthy_pet.DTOs.response.CitaResponse;
import api_healthy_pet.Entities.Cita;
import api_healthy_pet.Entities.Mascota;
import api_healthy_pet.Entities.Recepcionista;
import api_healthy_pet.Entities.Servicio;
import api_healthy_pet.Entities.Veterinario;
import api_healthy_pet.Enums.EstadoCita;
import api_healthy_pet.Enums.RolUsuario;
import api_healthy_pet.Exceptions.BadRequestException;
import api_healthy_pet.Exceptions.ForbiddenException;
import api_healthy_pet.Exceptions.ResourceNotFoundException;
import api_healthy_pet.Mappers.CitaMapper;
import api_healthy_pet.Repositories.CitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CitaService {

    private final CitaRepository citaRepository;
    private final CitaMapper citaMapper;
    private final RecepcionistaService recepcionistaService;
    private final ServicioService servicioService;
    private final MascotaService mascotaService;
    private final VeterinarioService veterinarioService;
    private final CurrentUserService currentUserService;

    public List<CitaResponse> findAll() {
        return citaRepository.findAll().stream()
                .map(citaMapper::toResponse)
                .toList();
    }

    public CitaResponse findById(Long idCita) {
        Cita cita = getCita(idCita);
        validateReadAccess(cita);
        return citaMapper.toResponse(cita);
    }

    @Transactional
    public CitaResponse create(CitaRequest request) {
        Cita cita = new Cita();
        applyRequest(cita, request);
        cita.setEstado(EstadoCita.PROGRAMADA);
        return citaMapper.toResponse(citaRepository.save(cita));
    }

    @Transactional
    public CitaResponse update(Long idCita, CitaRequest request) {
        Cita cita = getCita(idCita);
        validateUpdatableState(cita);
        applyRequest(cita, request);
        return citaMapper.toResponse(citaRepository.save(cita));
    }

    @Transactional
    public CitaResponse changeEstado(Long idCita, CitaEstadoRequest request) {
        Cita cita = getCita(idCita);
        validateTransition(cita.getEstado(), request.getEstado());
        cita.setEstado(request.getEstado());
        return citaMapper.toResponse(citaRepository.save(cita));
    }

    public List<CitaResponse> findMine() {
        RolUsuario rol = currentUserService.getCurrentRole();
        List<Cita> citas;
        if (rol == RolUsuario.CLIENTE) {
            citas = citaRepository.findByMascota_Cliente_IdUsuario(currentUserService.getCurrentUserId());
        } else if (rol == RolUsuario.VETERINARIO) {
            citas = citaRepository.findByVeterinario_IdUsuario(currentUserService.getCurrentUserId());
        } else {
            throw new ForbiddenException("Solo clientes y veterinarios pueden consultar sus citas");
        }

        return citas.stream()
                .map(citaMapper::toResponse)
                .toList();
    }

    public List<CitaResponse> findByVeterinario(Long idUsuarioVeterinario) {
        if (currentUserService.getCurrentRole() == RolUsuario.VETERINARIO
                && !currentUserService.isCurrentUser(idUsuarioVeterinario)) {
            throw new ForbiddenException("No puede consultar citas de otro veterinario");
        }
        return citaRepository.findByVeterinario_IdUsuario(idUsuarioVeterinario).stream()
                .map(citaMapper::toResponse)
                .toList();
    }

    public List<CitaResponse> findByCliente(Long idUsuarioCliente) {
        if (currentUserService.getCurrentRole() == RolUsuario.CLIENTE
                && !currentUserService.isCurrentUser(idUsuarioCliente)) {
            throw new ForbiddenException("No puede consultar citas de otro cliente");
        }
        return citaRepository.findByMascota_Cliente_IdUsuario(idUsuarioCliente).stream()
                .map(citaMapper::toResponse)
                .toList();
    }

    public Cita getCita(Long idCita) {
        return citaRepository.findById(idCita)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada"));
    }

    private void applyRequest(Cita cita, CitaRequest request) {
        Recepcionista recepcionista = recepcionistaService.getRecepcionista(request.getIdUsuarioRecepcionista());
        Servicio servicio = servicioService.getServicio(request.getIdServicio());
        Mascota mascota = mascotaService.getMascota(request.getIdMascota());
        Veterinario veterinario = veterinarioService.getVeterinario(request.getIdUsuarioVeterinario());

        if (!Boolean.TRUE.equals(servicio.getActivo())) {
            throw new BadRequestException("No se puede programar una cita con un servicio inactivo");
        }
        if (!Boolean.TRUE.equals(mascota.getActivo())) {
            throw new BadRequestException("No se puede programar una cita para una mascota inactiva");
        }

        cita.setMotivo(request.getMotivo());
        cita.setNotas(request.getNotas());
        cita.setFechaProgramada(request.getFechaProgramada());
        cita.setRecepcionista(recepcionista);
        cita.setServicio(servicio);
        cita.setMascota(mascota);
        cita.setVeterinario(veterinario);
    }

    private void validateReadAccess(Cita cita) {
        RolUsuario rol = currentUserService.getCurrentRole();
        Long currentId = currentUserService.getCurrentUserId();

        if (rol == RolUsuario.ADMIN || rol == RolUsuario.RECEPCIONISTA) {
            return;
        }
        if (rol == RolUsuario.VETERINARIO && cita.getVeterinario().getIdUsuario().equals(currentId)) {
            return;
        }
        if (rol == RolUsuario.CLIENTE && cita.getMascota().getCliente().getIdUsuario().equals(currentId)) {
            return;
        }

        throw new ForbiddenException("No tiene permisos para acceder a esta cita");
    }

    private void validateUpdatableState(Cita cita) {
        if (cita.getEstado() == EstadoCita.FINALIZADA || cita.getEstado() == EstadoCita.CANCELADA) {
            throw new BadRequestException("No se puede modificar una cita finalizada o cancelada");
        }
    }

    private void validateTransition(EstadoCita actual, EstadoCita nuevo) {
        if (actual == EstadoCita.FINALIZADA || actual == EstadoCita.CANCELADA) {
            throw new BadRequestException("La cita ya no puede cambiar de estado");
        }
        if (actual == EstadoCita.PROGRAMADA
                && (nuevo == EstadoCita.EN_CURSO || nuevo == EstadoCita.CANCELADA)) {
            return;
        }
        if (actual == EstadoCita.EN_CURSO && nuevo == EstadoCita.FINALIZADA) {
            return;
        }
        throw new BadRequestException("La transicion de estado de cita no es valida");
    }
}
