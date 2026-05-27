package api_healthy_pet.Repositories;

import api_healthy_pet.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
