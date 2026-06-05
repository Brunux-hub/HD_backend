package api_healthy_pet.Services;

import api_healthy_pet.Dtos.Request.LoginRequest;
import api_healthy_pet.Dtos.Request.RegisterRequest;
import api_healthy_pet.Dtos.Response.AuthResponse;
import api_healthy_pet.Dtos.Response.UserResponse;
import api_healthy_pet.Entities.User;
import api_healthy_pet.Exceptions.UserException;
import api_healthy_pet.Mappers.UserMapper;
import api_healthy_pet.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.username());

        return new AuthResponse(jwtService.generateToken(userDetails));
    }

    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new UserException("El username ya está registrado");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setType(request.type());

        return userMapper.toResponse(userRepository.save(user));
    }
}
