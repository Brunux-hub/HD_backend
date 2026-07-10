package api_healthy_pet.Services;

import api_healthy_pet.Entities.Usuario;
import api_healthy_pet.Enums.RolUsuario;
import api_healthy_pet.Exceptions.BadRequestException;
import api_healthy_pet.Exceptions.DuplicateResourceException;
import api_healthy_pet.Repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class AccountServiceSupport {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario createUsuario(String correo, String contrasenia, RolUsuario rol, Boolean habilitado) {
        if (!StringUtils.hasText(contrasenia)) {
            throw new BadRequestException("La contrasenia es obligatoria para la creacion");
        }
        if (usuarioRepository.existsByCorreo(correo)) {
            throw new DuplicateResourceException("Ya existe un usuario con el correo indicado");
        }

        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setContraseniaHash(passwordEncoder.encode(contrasenia));
        usuario.setRol(rol);
        usuario.setHabilitado(habilitado == null ? Boolean.TRUE : habilitado);
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Usuario usuario, String correo, String contrasenia, Boolean habilitado) {
        if (usuarioRepository.existsByCorreoAndIdUsuarioNot(correo, usuario.getIdUsuario())) {
            throw new DuplicateResourceException("Ya existe un usuario con el correo indicado");
        }

        usuario.setCorreo(correo);
        if (StringUtils.hasText(contrasenia)) {
            usuario.setContraseniaHash(passwordEncoder.encode(contrasenia));
        }
        if (habilitado != null) {
            usuario.setHabilitado(habilitado);
        }
        return usuarioRepository.save(usuario);
    }
}
