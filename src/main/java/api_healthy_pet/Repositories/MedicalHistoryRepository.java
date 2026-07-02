package api_healthy_pet.Repositories;

import api_healthy_pet.Entities.MedicalHistory;
import api_healthy_pet.Enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {
    List<MedicalHistory> findByAppointmentStatus(AppointmentStatus status);

    List<MedicalHistory> findByAppointment_Pet_IdPet(Long idPet);
}
