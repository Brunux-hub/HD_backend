package api_healthy_pet.Services;

import api_healthy_pet.DTOs.request.AdministradorRequest;
import api_healthy_pet.DTOs.response.AdministradorResponse;
import api_healthy_pet.Entities.Administrador;
import api_healthy_pet.Entities.Usuario;
import api_healthy_pet.Enums.RolUsuario;
import api_healthy_pet.Exceptions.ResourceNotFoundException;
import api_healthy_pet.Mappers.AdministradorMapper;
import api_healthy_pet.Repositories.AdministradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdministradorService {

    private final AdministradorRepository administradorRepository;
    private final AdministradorMapper administradorMapper;
    private final AccountServiceSupport accountServiceSupport;

    public List<AdministradorResponse> findAll() {
        return administradorRepository.findAll().stream()
                .map(administradorMapper::toResponse)
                .toList();
    }

    public AdministradorResponse findById(Long idUsuario) {
        return administradorMapper.toResponse(getAdministrador(idUsuario));
    }

    @Transactional
    public AdministradorResponse create(AdministradorRequest request) {
        Usuario usuario = accountServiceSupport.createUsuario(
                request.getCorreo(),
                request.getContrasenia(),
                RolUsuario.ADMIN,
                request.getHabilitado()
        );

        Administrador administrador = new Administrador();
        administrador.setUsuario(usuario);
        administrador.setNombres(request.getNombres());
        administrador.setApellidos(request.getApellidos());
        return administradorMapper.toResponse(administradorRepository.save(administrador));
    }

    @Transactional
    public AdministradorResponse update(Long idUsuario, AdministradorRequest request) {
        Administrador administrador = getAdministrador(idUsuario);
        accountServiceSupport.updateUsuario(
                administrador.getUsuario(),
                request.getCorreo(),
                request.getContrasenia(),
                request.getHabilitado()
        );
        administrador.setNombres(request.getNombres());
        administrador.setApellidos(request.getApellidos());
        return administradorMapper.toResponse(administradorRepository.save(administrador));
    }

    private Administrador getAdministrador(Long idUsuario) {
        return administradorRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no encontrado"));
    }
}
