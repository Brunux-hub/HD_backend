package api_healthy_pet.Configuration;

import api_healthy_pet.Entities.User;
import api_healthy_pet.Enums.UserType;
import api_healthy_pet.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataSeeder {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    @Order(1)
    public void seedAdminUser() {
        if (!userRepository.existsByUsername("admin@healty.admin.pe")) {
            User admin = new User();
            admin.setUsername("admin@healty.admin.pe");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setType(UserType.ADMIN);
            userRepository.save(admin);
            log.info("Usuario ADMIN creado: admin@healty.admin.pe / admin123");
        }
    }
}
