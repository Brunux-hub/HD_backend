package api_healthy_pet.Services;

import api_healthy_pet.DTOs.request.VeterinarioRequest;
import api_healthy_pet.DTOs.response.VeterinarioResponse;
import api_healthy_pet.Entities.Usuario;
import api_healthy_pet.Entities.Veterinario;
import api_healthy_pet.Enums.RolUsuario;
import api_healthy_pet.Exceptions.BadRequestException;
import api_healthy_pet.Exceptions.DuplicateResourceException;
import api_healthy_pet.Exceptions.ResourceNotFoundException;
import api_healthy_pet.Mappers.VeterinarioMapper;
import api_healthy_pet.Repositories.VeterinarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;
    private final VeterinarioMapper veterinarioMapper;
    private final AccountServiceSupport accountServiceSupport;

    public List<VeterinarioResponse> findAll() {
        return veterinarioRepository.findAll().stream()
                .map(veterinarioMapper::toResponse)
                .toList();
    }

    public VeterinarioResponse findById(Long idUsuario) {
        return veterinarioMapper.toResponse(getVeterinario(idUsuario));
    }

    @Transactional
    public VeterinarioResponse create(VeterinarioRequest request) {
        validateEspecialidades(request.getEspecialidades());
        if (veterinarioRepository.existsByNumeroLicencia(request.getNumeroLicencia())) {
            throw new DuplicateResourceException("Ya existe un veterinario con ese numero de licencia");
        }

        Usuario usuario = accountServiceSupport.createUsuario(
                request.getCorreo(),
                request.getContrasenia(),
                RolUsuario.VETERINARIO,
                request.getHabilitado()
        );

        Veterinario veterinario = new Veterinario();
        veterinario.setUsuario(usuario);
        veterinario.setNombres(request.getNombres());
        veterinario.setApellidos(request.getApellidos());
        veterinario.setTelefono(request.getTelefono());
        veterinario.setNumeroLicencia(request.getNumeroLicencia());
        veterinario.setEspecialidades(request.getEspecialidades());
        return veterinarioMapper.toResponse(veterinarioRepository.save(veterinario));
    }

    @Transactional
    public VeterinarioResponse update(Long idUsuario, VeterinarioRequest request) {
        Veterinario veterinario = getVeterinario(idUsuario);
        validateEspecialidades(request.getEspecialidades());
        if (veterinarioRepository.existsByNumeroLicenciaAndIdUsuarioNot(request.getNumeroLicencia(), idUsuario)) {
            throw new DuplicateResourceException("Ya existe un veterinario con ese numero de licencia");
        }

        accountServiceSupport.updateUsuario(
                veterinario.getUsuario(),
                request.getCorreo(),
                request.getContrasenia(),
                request.getHabilitado()
        );
        veterinario.setNombres(request.getNombres());
        veterinario.setApellidos(request.getApellidos());
        veterinario.setTelefono(request.getTelefono());
        veterinario.setNumeroLicencia(request.getNumeroLicencia());
        veterinario.setEspecialidades(request.getEspecialidades());
        return veterinarioMapper.toResponse(veterinarioRepository.save(veterinario));
    }

    private void validateEspecialidades(Set<?> especialidades) {
        if (especialidades == null || especialidades.size() < 1 || especialidades.size() > 2) {
            throw new BadRequestException("El veterinario debe tener entre 1 y 2 especialidades");
        }
    }

    public Veterinario getVeterinario(Long idUsuario) {
        return veterinarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinario no encontrado"));
    }
}
