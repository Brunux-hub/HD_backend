package api_healthy_pet.Repositories;

import api_healthy_pet.Entities.Appointment;
import api_healthy_pet.Enums.AppointmentStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Override
    @EntityGraph(attributePaths = {
            "receptionista", "receptionista.user",
            "pet", "pet.cliente",
            "veterinario", "veterinario.user"
    })
    List<Appointment> findAll();

    @Override
    @EntityGraph(attributePaths = {
            "receptionista", "receptionista.user",
            "pet", "pet.cliente",
            "veterinario", "veterinario.user"
    })
    Optional<Appointment> findById(Long idAppointment);

    // SELECT * FROM appointments WHERE date >= ?;
    @EntityGraph(attributePaths = {
            "receptionista", "receptionista.user",
            "pet", "pet.cliente",
            "veterinario", "veterinario.user"
    })
    List<Appointment> findByDateGreaterThanEqual(LocalDateTime date);

    // SELECT * FROM appointments WHERE status = ?; (OPENED, CLOSED, CANCELED, RESCHEDULED)
    @EntityGraph(attributePaths = {
            "receptionista", "receptionista.user",
            "pet", "pet.cliente",
            "veterinario", "veterinario.user"
    })
    List<Appointment> findByStatus(AppointmentStatus status);

    // SELECT * FROM appointments WHERE status = ? AND date >= ?;
    @EntityGraph(attributePaths = {
            "receptionista", "receptionista.user",
            "pet", "pet.cliente",
            "veterinario", "veterinario.user"
    })
    List<Appointment> findByStatusAndDateGreaterThanEqual(AppointmentStatus status, LocalDateTime date);
}
