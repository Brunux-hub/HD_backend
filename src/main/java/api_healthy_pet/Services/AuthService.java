package api_healthy_pet.Services;

import api_healthy_pet.DTOs.request.LoginRequest;
import api_healthy_pet.DTOs.response.AuthMeResponse;
import api_healthy_pet.DTOs.response.AuthResponse;
import api_healthy_pet.Entities.Usuario;
import api_healthy_pet.Exceptions.ResourceNotFoundException;
import api_healthy_pet.Repositories.UsuarioRepository;
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
    private final CurrentUserService currentUserService;

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getContrasenia())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Usuario usuario = usuarioRepository.findByCorreo(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        AuthResponse response = new AuthResponse();
        response.setToken(jwtService.generateToken(userDetails));
        return response;
    }

    public AuthMeResponse getCurrentUser() {
        Usuario usuario = currentUserService.getCurrentUsuario();
        AuthMeResponse response = new AuthMeResponse();
        response.setIdUsuario(usuario.getIdUsuario());
        response.setCorreo(usuario.getCorreo());
        response.setRol(usuario.getRol());
        return response;
    }
}
