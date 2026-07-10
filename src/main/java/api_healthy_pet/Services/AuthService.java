package api_healthy_pet.Services;

import api_healthy_pet.DTOs.request.LoginRequest;
import api_healthy_pet.DTOs.response.AuthResponse;
import api_healthy_pet.Entities.Usuario;
import api_healthy_pet.Enums.RolUsuario;
import api_healthy_pet.Exceptions.ResourceNotFoundException;
import api_healthy_pet.Repositories.AdministradorRepository;
import api_healthy_pet.Repositories.ClienteRepository;
import api_healthy_pet.Repositories.RecepcionistaRepository;
import api_healthy_pet.Repositories.UsuarioRepository;
import api_healthy_pet.Repositories.VeterinarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final AdministradorRepository administradorRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final RecepcionistaRepository recepcionistaRepository;
    private final ClienteRepository clienteRepository;

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getContrasenia())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Usuario usuario = usuarioRepository.findByCorreo(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        AuthResponse response = new AuthResponse();
        response.setToken(jwtService.generateToken(userDetails));
        response.setIdUsuario(usuario.getIdUsuario());
        response.setCorreo(usuario.getCorreo());
        response.setRol(usuario.getRol());
        response.setHabilitado(usuario.getHabilitado());
        setProfileNames(response, usuario);
        return response;
    }

    private void setProfileNames(AuthResponse response, Usuario usuario) {
        RolUsuario rol = usuario.getRol();
        switch (rol) {
            case ADMIN -> administradorRepository.findById(usuario.getIdUsuario()).ifPresent(admin -> {
                response.setNombres(admin.getNombres());
                response.setApellidos(admin.getApellidos());
            });
            case VETERINARIO -> veterinarioRepository.findById(usuario.getIdUsuario()).ifPresent(veterinario -> {
                response.setNombres(veterinario.getNombres());
                response.setApellidos(veterinario.getApellidos());
            });
            case RECEPCIONISTA -> recepcionistaRepository.findById(usuario.getIdUsuario()).ifPresent(recepcionista -> {
                response.setNombres(recepcionista.getNombres());
                response.setApellidos(recepcionista.getApellidos());
            });
            case CLIENTE -> clienteRepository.findById(usuario.getIdUsuario()).ifPresent(cliente -> {
                response.setNombres(cliente.getNombres());
                response.setApellidos(cliente.getApellidos());
            });
            default -> {
            }
        }
    }
}
