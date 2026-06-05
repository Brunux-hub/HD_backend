package api_healthy_pet.Repositories;

import api_healthy_pet.Entities.Appointment;
import api_healthy_pet.Enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    // SELECT * FROM appointments WHERE date >= ?;
    List<Appointment> findByDateGreaterThanEqual(LocalDateTime date);

    // SELECT * FROM appointments WHERE status = ?; (OPENED, CLOSED, CANCELED, RESCHEDULED)
    List<Appointment> findByStatus(AppointmentStatus status);

    // SELECT * FROM appointments WHERE status = ? AND date >= ?;
    List<Appointment> findByStatusAndDateGreaterThanEqual(AppointmentStatus status, LocalDateTime date);
}
