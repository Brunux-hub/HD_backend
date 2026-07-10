package api_healthy_pet.Services;

import api_healthy_pet.DTOs.request.MascotaRequest;
import api_healthy_pet.DTOs.response.MascotaResponse;
import api_healthy_pet.Entities.Cliente;
import api_healthy_pet.Entities.Mascota;
import api_healthy_pet.Enums.RolUsuario;
import api_healthy_pet.Exceptions.ForbiddenException;
import api_healthy_pet.Exceptions.ResourceNotFoundException;
import api_healthy_pet.Mappers.MascotaMapper;
import api_healthy_pet.Repositories.MascotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final MascotaMapper mascotaMapper;
    private final ClienteService clienteService;
    private final CurrentUserService currentUserService;

    public List<MascotaResponse> findAll() {
        return mascotaRepository.findAll().stream()
                .map(mascotaMapper::toResponse)
                .toList();
    }

    public MascotaResponse findById(Long idMascota) {
        Mascota mascota = getMascota(idMascota);
        validateMascotaAccess(mascota);
        return mascotaMapper.toResponse(mascota);
    }

    @Transactional
    public MascotaResponse create(MascotaRequest request) {
        Cliente cliente = clienteService.getCliente(request.getIdUsuarioCliente());
        Mascota mascota = new Mascota();
        applyRequest(mascota, request, cliente);
        return mascotaMapper.toResponse(mascotaRepository.save(mascota));
    }

    @Transactional
    public MascotaResponse update(Long idMascota, MascotaRequest request) {
        Mascota mascota = getMascota(idMascota);
        Cliente cliente = clienteService.getCliente(request.getIdUsuarioCliente());
        applyRequest(mascota, request, cliente);
        return mascotaMapper.toResponse(mascotaRepository.save(mascota));
    }

    @Transactional
    public void activate(Long idMascota) {
        Mascota mascota = getMascota(idMascota);
        mascota.setActivo(Boolean.TRUE);
        mascotaRepository.save(mascota);
    }

    @Transactional
    public void deactivate(Long idMascota) {
        Mascota mascota = getMascota(idMascota);
        mascota.setActivo(Boolean.FALSE);
        mascotaRepository.save(mascota);
    }

    public List<MascotaResponse> findByCliente(Long idUsuarioCliente) {
        validateClienteReadAccess(idUsuarioCliente);
        return mascotaRepository.findByCliente_IdUsuarioAndActivoTrue(idUsuarioCliente).stream()
                .map(mascotaMapper::toResponse)
                .toList();
    }

    public List<MascotaResponse> findMine() {
        if (currentUserService.getCurrentRole() != RolUsuario.CLIENTE) {
            throw new ForbiddenException("Solo un cliente puede consultar sus mascotas");
        }
        return findByCliente(currentUserService.getCurrentUserId());
    }

    public Mascota getMascota(Long idMascota) {
        return mascotaRepository.findById(idMascota)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada"));
    }

    private void applyRequest(Mascota mascota, MascotaRequest request, Cliente cliente) {
        mascota.setNombre(request.getNombre());
        mascota.setEspecie(request.getEspecie());
        mascota.setRaza(request.getRaza());
        mascota.setSexo(request.getSexo());
        mascota.setFechaNacimiento(request.getFechaNacimiento());
        mascota.setCliente(cliente);
        if (request.getActivo() != null) {
            mascota.setActivo(request.getActivo());
        } else if (mascota.getIdMascota() == null) {
            mascota.setActivo(Boolean.TRUE);
        }
    }

    private void validateMascotaAccess(Mascota mascota) {
        if (currentUserService.getCurrentRole() == RolUsuario.CLIENTE
                && !currentUserService.isCurrentUser(mascota.getCliente().getIdUsuario())) {
            throw new ForbiddenException("No puede acceder a mascotas de otro cliente");
        }
    }

    private void validateClienteReadAccess(Long idUsuarioCliente) {
        if (currentUserService.getCurrentRole() == RolUsuario.CLIENTE
                && !currentUserService.isCurrentUser(idUsuarioCliente)) {
            throw new ForbiddenException("No puede acceder a mascotas de otro cliente");
        }
    }
}
