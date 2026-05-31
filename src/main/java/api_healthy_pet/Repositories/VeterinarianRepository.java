package api_healthy_pet.Repositories;

import api_healthy_pet.Entities.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {

    @Query("SELECT v FROM Veterinarian v WHERE v.idVeterinarian NOT IN " +
            "(SELECT a.veterinarian.idVeterinarian FROM Appointment a WHERE a.date = :date)")
    List<Veterinarian> findAvailableVeterinarians(@Param("date") LocalDateTime date);

}
