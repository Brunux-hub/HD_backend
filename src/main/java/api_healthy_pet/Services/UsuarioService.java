package api_healthy_pet.Services;

import api_healthy_pet.DTOs.request.UsuarioRequest;
import api_healthy_pet.DTOs.response.UsuarioResponse;
import api_healthy_pet.Entities.Usuario;
import api_healthy_pet.Exceptions.BadRequestException;
import api_healthy_pet.Exceptions.ResourceNotFoundException;
import api_healthy_pet.Mappers.UsuarioMapper;
import api_healthy_pet.Repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final AccountServiceSupport accountServiceSupport;

    public List<UsuarioResponse> findAll() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toResponse)
                .toList();
    }

    public UsuarioResponse findById(Long idUsuario) {
        return usuarioMapper.toResponse(getUsuario(idUsuario));
    }

    @Transactional
    public UsuarioResponse create(UsuarioRequest request) {
        Usuario usuario = accountServiceSupport.createUsuario(
                request.getCorreo(),
                request.getContrasenia(),
                request.getRol(),
                request.getHabilitado()
        );
        return usuarioMapper.toResponse(usuario);
    }

    @Transactional
    public UsuarioResponse update(Long idUsuario, UsuarioRequest request) {
        Usuario usuario = getUsuario(idUsuario);
        if (request.getRol() != usuario.getRol()) {
            throw new BadRequestException("No se permite cambiar el rol del usuario desde este endpoint");
        }
        return usuarioMapper.toResponse(
                accountServiceSupport.updateUsuario(
                        usuario,
                        request.getCorreo(),
                        request.getContrasenia(),
                        request.getHabilitado()
                )
        );
    }

    @Transactional
    public void activate(Long idUsuario) {
        Usuario usuario = getUsuario(idUsuario);
        usuario.setHabilitado(Boolean.TRUE);
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void deactivate(Long idUsuario) {
        Usuario usuario = getUsuario(idUsuario);
        usuario.setHabilitado(Boolean.FALSE);
        usuarioRepository.save(usuario);
    }

    public Usuario getUsuario(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }
}
