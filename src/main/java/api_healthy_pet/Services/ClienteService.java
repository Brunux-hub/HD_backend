package api_healthy_pet.Services;

import api_healthy_pet.DTOs.request.ClienteRequest;
import api_healthy_pet.DTOs.response.ClienteResponse;
import api_healthy_pet.Entities.Cliente;
import api_healthy_pet.Entities.Usuario;
import api_healthy_pet.Enums.RolUsuario;
import api_healthy_pet.Exceptions.DuplicateResourceException;
import api_healthy_pet.Exceptions.ForbiddenException;
import api_healthy_pet.Exceptions.ResourceNotFoundException;
import api_healthy_pet.Mappers.ClienteMapper;
import api_healthy_pet.Repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final AccountServiceSupport accountServiceSupport;
    private final CurrentUserService currentUserService;

    public List<ClienteResponse> findAll() {
        return clienteRepository.findAll().stream()
                .map(clienteMapper::toResponse)
                .toList();
    }

    public ClienteResponse findById(Long idUsuario) {
        Cliente cliente = getCliente(idUsuario);
        validateClienteAccess(cliente.getIdUsuario());
        return clienteMapper.toResponse(cliente);
    }

    public ClienteResponse findByDni(String dni) {
        Cliente cliente = clienteRepository.findByDni(dni)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        validateClienteAccess(cliente.getIdUsuario());
        return clienteMapper.toResponse(cliente);
    }

    public ClienteResponse findMine() {
        if (currentUserService.getCurrentRole() != RolUsuario.CLIENTE) {
            throw new ForbiddenException("Solo un cliente autenticado puede consultar este endpoint");
        }
        return clienteMapper.toResponse(getCliente(currentUserService.getCurrentUserId()));
    }

    @Transactional
    public ClienteResponse create(ClienteRequest request) {
        validateDniForCreate(request.getDni());
        Usuario usuario = accountServiceSupport.createUsuario(
                request.getCorreo(),
                request.getContrasenia(),
                RolUsuario.CLIENTE,
                request.getHabilitado()
        );

        Cliente cliente = new Cliente();
        cliente.setUsuario(usuario);
        cliente.setNombres(request.getNombres());
        cliente.setApellidos(request.getApellidos());
        cliente.setDni(request.getDni());
        cliente.setTelefono(request.getTelefono());
        cliente.setDireccion(request.getDireccion());
        return clienteMapper.toResponse(clienteRepository.save(cliente));
    }

    @Transactional
    public ClienteResponse update(Long idUsuario, ClienteRequest request) {
        Cliente cliente = getCliente(idUsuario);
        validateClienteAccessForWrite(idUsuario);
        validateDniForUpdate(request.getDni(), idUsuario);

        accountServiceSupport.updateUsuario(
                cliente.getUsuario(),
                request.getCorreo(),
                request.getContrasenia(),
                request.getHabilitado()
        );
        cliente.setNombres(request.getNombres());
        cliente.setApellidos(request.getApellidos());
        cliente.setDni(request.getDni());
        cliente.setTelefono(request.getTelefono());
        cliente.setDireccion(request.getDireccion());
        return clienteMapper.toResponse(clienteRepository.save(cliente));
    }

    public Cliente getCliente(Long idUsuario) {
        return clienteRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
    }

    private void validateDniForCreate(String dni) {
        if (StringUtils.hasText(dni) && clienteRepository.existsByDni(dni)) {
            throw new DuplicateResourceException("Ya existe un cliente con ese DNI");
        }
    }

    private void validateDniForUpdate(String dni, Long idUsuario) {
        if (StringUtils.hasText(dni) && clienteRepository.existsByDniAndIdUsuarioNot(dni, idUsuario)) {
            throw new DuplicateResourceException("Ya existe un cliente con ese DNI");
        }
    }

    private void validateClienteAccess(Long idUsuarioObjetivo) {
        if (currentUserService.getCurrentRole() == RolUsuario.CLIENTE
                && !currentUserService.isCurrentUser(idUsuarioObjetivo)) {
            throw new ForbiddenException("No puede acceder a datos de otro cliente");
        }
    }

    private void validateClienteAccessForWrite(Long idUsuarioObjetivo) {
        RolUsuario rol = currentUserService.getCurrentRole();
        if (rol == RolUsuario.CLIENTE && !currentUserService.isCurrentUser(idUsuarioObjetivo)) {
            throw new ForbiddenException("No puede modificar datos de otro cliente");
        }
    }
}
