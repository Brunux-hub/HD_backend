package api_healthy_pet.Configuration;

import api_healthy_pet.Entities.Administrador;
import api_healthy_pet.Entities.Usuario;
import api_healthy_pet.Enums.RolUsuario;
import api_healthy_pet.Repositories.AdministradorRepository;
import api_healthy_pet.Repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final AdministradorRepository administradorRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        Usuario usuario = usuarioRepository.findByCorreo("admin@healthypets.com")
                .orElseGet(() -> {
                    Usuario nuevoUsuario = new Usuario();
                    nuevoUsuario.setCorreo("admin@healthypets.com");
                    nuevoUsuario.setContraseniaHash(passwordEncoder.encode("admin123"));
                    nuevoUsuario.setRol(RolUsuario.ADMIN);
                    nuevoUsuario.setHabilitado(Boolean.TRUE);
                    return usuarioRepository.save(nuevoUsuario);
                });

        administradorRepository.findById(usuario.getIdUsuario())
                .orElseGet(() -> {
                    Administrador administrador = new Administrador();
                    administrador.setUsuario(usuario);
                    administrador.setNombres("Admin");
                    administrador.setApellidos("HealthyPets");
                    return administradorRepository.save(administrador);
                });
    }
}
