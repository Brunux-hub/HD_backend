package api_healthy_pet.Repositories;

import api_healthy_pet.Entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
