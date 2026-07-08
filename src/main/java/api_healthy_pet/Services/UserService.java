package api_healthy_pet.Services;

import api_healthy_pet.Dtos.Request.RegistroRequest;
import api_healthy_pet.Dtos.Request.UserRequest;
import api_healthy_pet.Dtos.Response.UserResponse;
import api_healthy_pet.Entities.*;
import api_healthy_pet.Enums.Role;
import api_healthy_pet.Exceptions.UserException;
import api_healthy_pet.Mappers.UserMapper;
import api_healthy_pet.Repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final RecepcionistaRepository recepcionistaRepository;
    private final ClienteRepository clienteRepository;

    @Transactional
    public UserResponse registrar(RegistroRequest request) {
        String emailCreador = SecurityContextHolder.getContext().getAuthentication().getName();
        User creador = userRepository.findByEmail(emailCreador)
                .orElseThrow(() -> new UserException("Creador no encontrado"));

        validarPermiso(creador.getRole(), request.role());

        if (userRepository.existsByEmail(request.email())) {
            throw new UserException("El email ya está registrado");
        }

        if (request.role() == Role.VETERINARIO) {
            if (request.especialidades() == null || request.especialidades().isEmpty()) {
                throw new UserException("El veterinario debe tener al menos 1 especialidad");
            }
            if (request.especialidades().size() > 2) {
                throw new UserException("El veterinario puede tener máximo 2 especialidades");
            }
            if (request.licencia() == null || request.licencia().isBlank()) {
                throw new UserException("La licencia es obligatoria para el veterinario");
            }
        }

        User user = new User();
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.role());
        user.setEnabled(true);
        userRepository.save(user);

        switch (request.role()) {
            case ADMIN -> {
                Admin admin = new Admin();
                admin.setUser(user);
                admin.setNombre(request.nombre());
                admin.setApellido(request.apellido());
                adminRepository.save(admin);
            }
            case VETERINARIO -> {
                Veterinario vet = new Veterinario();
                vet.setUser(user);
                vet.setNombre(request.nombre());
                vet.setApellido(request.apellido());
                vet.setTelefono(request.telefono());
                vet.setEspecialidades(request.especialidades());
                vet.setLicencia(request.licencia());
                veterinarioRepository.save(vet);
            }
            case RECEPCIONISTA -> {
                Recepcionista recep = new Recepcionista();
                recep.setUser(user);
                recep.setNombre(request.nombre());
                recep.setApellido(request.apellido());
                recep.setTelefono(request.telefono());
                recepcionistaRepository.save(recep);
            }
            case CLIENTE -> {
                Cliente cliente = new Cliente();
                cliente.setUser(user);
                cliente.setNombre(request.nombre());
                cliente.setApellido(request.apellido());
                cliente.setTelefono(request.telefono());
                cliente.setDireccion(request.direccion());
                clienteRepository.save(cliente);
            }
        }

        return new UserResponse(user.getId(), user.getEmail(), user.getRole());
    }

    private void validarPermiso(Role creador, Role solicitado) {
        if (creador == Role.ADMIN) {
            return;
        }
        if (creador == Role.RECEPCIONISTA && solicitado == Role.CLIENTE) {
            return;
        }
        throw new AccessDeniedException("No tienes permiso para crear usuarios con rol " + solicitado);
    }

    public UserResponse findById (Long id){
        return userRepository.findById(id)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new UserException("Usuario no encontrado"));
    }

    public List<UserResponse> findAll(){
        return userRepository.findAll()
                .stream().map(userMapper::toResponse).toList();
    }

    public UserResponse updateUser (Long id, UserRequest request){
        User user = userRepository.findById(id)
                        .orElseThrow(() -> new UserException("Usuario no encontrado"));

        validateEmailAvailability(request.email(), id);
        userMapper.updateEntityFromRequest(request, user);
        user.setPassword(passwordEncoder.encode(request.password()));

        return userMapper.toResponse(userRepository.save(user));
    }

    public void deleteById (Long id) {
        if (!userRepository.existsById(id)){
            throw new UserException("Usuario no encontrado");
        }
        userRepository.deleteById(id);
    }

    private void validateEmailAvailability(String email, Long currentUserId) {
        userRepository.findByEmail(email)
                .filter(existingUser -> !existingUser.getId().equals(currentUserId))
                .ifPresent(existingUser -> {
                    throw new UserException("El email ya está registrado");
                });
    }
}
