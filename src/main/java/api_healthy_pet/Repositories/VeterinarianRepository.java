package api_healthy_pet.Repositories;

import api_healthy_pet.Entities.Veterinarian;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {

    @Override
    @EntityGraph(attributePaths = "user")
    List<Veterinarian> findAll();

    @Override
    @EntityGraph(attributePaths = "user")
    Optional<Veterinarian> findById(Long idVeterinarian);

    @EntityGraph(attributePaths = "user")
    @Query("SELECT v FROM Veterinarian v WHERE v.idVeterinarian NOT IN " +
            "(SELECT a.veterinarian.idVeterinarian FROM Appointment a WHERE a.date = :date)")
    List<Veterinarian> findAvailableVeterinarians(@Param("date") LocalDateTime date);

}
