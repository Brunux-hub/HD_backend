package api_healthy_pet.Services;

import api_healthy_pet.DTOs.request.CambiarContraseniaRequest;
import api_healthy_pet.DTOs.request.UsuarioRequest;
import api_healthy_pet.DTOs.response.UsuarioResponse;
import api_healthy_pet.Entities.Usuario;
import api_healthy_pet.Enums.RolUsuario;
import api_healthy_pet.Exceptions.BadRequestException;
import api_healthy_pet.Exceptions.ForbiddenException;
import api_healthy_pet.Exceptions.ResourceNotFoundException;
import api_healthy_pet.Mappers.UsuarioMapper;
import api_healthy_pet.Repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final AccountServiceSupport accountServiceSupport;
    private final CurrentUserService currentUserService;
    private final PasswordEncoder passwordEncoder;

    public List<UsuarioResponse> findAll() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toResponse)
                .toList();
    }

    public UsuarioResponse findById(Long idUsuario) {
        return usuarioMapper.toResponse(getUsuario(idUsuario));
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

    @Transactional
    public void changePassword(Long idUsuario, CambiarContraseniaRequest request) {
        Usuario usuario = getUsuario(idUsuario);

        boolean isAdmin = currentUserService.getCurrentRole() == RolUsuario.ADMIN;
        boolean isOwner = currentUserService.isCurrentUser(idUsuario);

        if (!isAdmin && !isOwner) {
            throw new ForbiddenException("No tiene permisos para cambiar la contrasenia de otro usuario");
        }

        if (isOwner && !isAdmin) {
            if (!StringUtils.hasText(request.getContraseniaActual())) {
                throw new BadRequestException("La contrasenia actual es obligatoria");
            }
            if (!passwordEncoder.matches(request.getContraseniaActual(), usuario.getContraseniaHash())) {
                throw new BadRequestException("La contrasenia actual es incorrecta");
            }
        }

        usuario.setContraseniaHash(passwordEncoder.encode(request.getNuevaContrasenia()));
        usuarioRepository.save(usuario);
    }

    public Usuario getUsuario(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }
}
