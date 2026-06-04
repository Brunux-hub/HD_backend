package api_healthy_pet.Services;

import api_healthy_pet.Configuration.JwtConfig;
import api_healthy_pet.Dtos.Request.LoginRequest;
import api_healthy_pet.Dtos.Request.RegisterRequest;
import api_healthy_pet.Dtos.Response.LoginResponse;
import api_healthy_pet.Entities.User;
import api_healthy_pet.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtConfig jwtConfig;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtConfig.generateToken(user.getEmail(), user.getRole());
        return new LoginResponse(token, user.getEmail(), user.getRole());
    }

    public void register(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() != null ? request.getRole() : "USER");
        userRepository.save(user);
    }
}