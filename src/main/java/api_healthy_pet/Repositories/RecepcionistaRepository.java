package api_healthy_pet.Repositories;

import api_healthy_pet.Entities.Recepcionista;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecepcionistaRepository extends JpaRepository<Recepcionista, Long> {

    @Override
    @EntityGraph(attributePaths = "user")
    List<Recepcionista> findAll();

    @Override
    @EntityGraph(attributePaths = "user")
    Optional<Recepcionista> findById(Long userId);
}
