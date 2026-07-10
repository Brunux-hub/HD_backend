package api_healthy_pet.Repositories;

import api_healthy_pet.Entities.RegistroMedico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistroMedicoRepository extends JpaRepository<RegistroMedico, Long> {

    Optional<RegistroMedico> findByCita_IdCita(Long idCita);

    boolean existsByCita_IdCita(Long idCita);
}
