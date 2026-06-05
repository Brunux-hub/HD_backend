package api_healthy_pet.Init;

import api_healthy_pet.Entities.User;
import api_healthy_pet.Enums.UserType;
import api_healthy_pet.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.init.enabled", havingValue = "true", matchIfMissing = false)
public class AdminSeed implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final Logger log = LoggerFactory.getLogger(AdminSeed.class);

    @Value("${ADMIN_USERNAME:admin}")
    private String adminUsername;

    @Value("${ADMIN_PASSWORD:}")
    private String adminPassword;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        if (adminPassword == null || adminPassword.isBlank()) {
            log.warn("ADMIN_PASSWORD not set; skipping admin seed");
            return;
        }

        userRepository.findByUsername(adminUsername).ifPresentOrElse(u -> {
            log.info("Admin user '{}' already exists, skipping seed", adminUsername);
        }, () -> {
            User admin = new User();
            admin.setUsername(adminUsername);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setType(UserType.ADMIN);
            userRepository.save(admin);
            log.info("Admin user '{}' created by seed", adminUsername);
        });
    }
}
