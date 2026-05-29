package api_healthy_pet.Repositories;

import api_healthy_pet.Entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {
}
