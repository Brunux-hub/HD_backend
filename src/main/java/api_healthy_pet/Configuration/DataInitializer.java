package api_healthy_pet.Configuration;

import api_healthy_pet.Entities.User;
import api_healthy_pet.Enums.UserType;
import api_healthy_pet.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.existsByType(UserType.ADMIN)) {
            return;
        }

        User admin = new User();
        admin.setUsername("admin01");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setType(UserType.ADMIN);

        userRepository.save(admin);
        System.out.println("Usuario ADMIN creado con username: admin01");
    }
}
