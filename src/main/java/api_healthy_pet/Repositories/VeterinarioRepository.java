package api_healthy_pet.Repositories;

import api_healthy_pet.Entities.Veterinario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {

    @Override
    @EntityGraph(attributePaths = "user")
    List<Veterinario> findAll();

    @Override
    @EntityGraph(attributePaths = "user")
    Optional<Veterinario> findById(Long userId);

    @EntityGraph(attributePaths = "user")
    @Query("SELECT v FROM Veterinario v WHERE v.userId NOT IN " +
            "(SELECT a.veterinario.userId FROM Appointment a WHERE a.date = :date)")
    List<Veterinario> findAvailableVeterinarios(@Param("date") LocalDateTime date);
}
