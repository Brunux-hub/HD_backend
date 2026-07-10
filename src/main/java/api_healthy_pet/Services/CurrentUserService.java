package api_healthy_pet.Services;

import api_healthy_pet.Entities.Usuario;
import api_healthy_pet.Enums.RolUsuario;
import api_healthy_pet.Exceptions.UnauthorizedException;
import api_healthy_pet.Repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class CurrentUserService {

    private final UsuarioRepository usuarioRepository;

    public Usuario getCurrentUsuario() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null
                || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new UnauthorizedException("No hay un usuario autenticado");
        }

        return usuarioRepository.findByCorreo(authentication.getName())
                .orElseThrow(() -> new UnauthorizedException("El usuario autenticado no existe"));
    }

    public Long getCurrentUserId() {
        return getCurrentUsuario().getIdUsuario();
    }

    public RolUsuario getCurrentRole() {
        return getCurrentUsuario().getRol();
    }

    public boolean isCurrentUser(Long idUsuario) {
        return getCurrentUserId().equals(idUsuario);
    }

    public boolean hasAnyRole(RolUsuario... roles) {
        RolUsuario currentRole = getCurrentRole();
        return Arrays.stream(roles).anyMatch(role -> role == currentRole);
    }
}
