package api_healthy_pet.Services;

import api_healthy_pet.Dtos.Request.ClientRegisterRequest;
import api_healthy_pet.Dtos.Request.LoginRequest;
import api_healthy_pet.Dtos.Request.RegisterRequest;
import api_healthy_pet.Dtos.Response.AuthResponse;
import api_healthy_pet.Dtos.Response.OwnerResponse;
import api_healthy_pet.Dtos.Response.UserResponse;
import api_healthy_pet.Entities.Owner;
import api_healthy_pet.Entities.User;
import api_healthy_pet.Enums.UserType;
import api_healthy_pet.Exceptions.UserException;
import api_healthy_pet.Mappers.OwnerMapper;
import api_healthy_pet.Mappers.UserMapper;
import api_healthy_pet.Repositories.OwnerRepository;
import api_healthy_pet.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    public AuthResponse login(LoginRequest request) {
        // authenticate() ya carga el usuario y valida la contraseña; reutilizamos
        // ese principal en vez de volver a consultar la BD (evita 1 viaje a Supabase).
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return new AuthResponse(jwtService.generateToken(userDetails));
    }

    // Alta de usuario (username/password/type) — uso administrativo.
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

    // Registro público de un CLIENTE: crea el User (login) y su Owner (ficha) con los mismos datos.
    // Un usuario sin ficha de veterinario ni de recepcionista se considera cliente.
    @Transactional
    public OwnerResponse registerClient(ClientRegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new UserException("El username ya está registrado");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setType(UserType.WORKER);
        userRepository.save(user);

        Owner owner = new Owner();
        owner.setUser(user);
        owner.setNames(request.names());
        owner.setLastNames(request.lastNames());
        owner.setEmail(request.email());
        owner.setPhoneNumber(request.phoneNumber());
        owner.setAddress(request.address());

        return ownerMapper.toResponse(ownerRepository.save(owner));
    }
}
