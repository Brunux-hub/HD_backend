package api_healthy_pet.Repositories;

import api_healthy_pet.Entities.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {
}
