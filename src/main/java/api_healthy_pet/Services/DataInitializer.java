package api_healthy_pet.Services;

import api_healthy_pet.Entities.Admin;
import api_healthy_pet.Entities.User;
import api_healthy_pet.Enums.Role;
import api_healthy_pet.Repositories.AdminRepository;
import api_healthy_pet.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findByEmail("admin@vet.com").isEmpty()) {
            User user = new User();
            user.setEmail("admin@vet.com");
            user.setPassword(passwordEncoder.encode("Admin123"));
            user.setRole(Role.ADMIN);
            user.setEnabled(true);
            userRepository.save(user);

            Admin admin = new Admin();
            admin.setUser(user);
            admin.setNombre("Admin");
            admin.setApellido("Principal");
            adminRepository.save(admin);

            System.out.println("Admin creado: admin@vet.com / Admin123");
        }
    }
}
