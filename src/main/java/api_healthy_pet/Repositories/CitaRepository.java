package api_healthy_pet.Repositories;

import api_healthy_pet.Entities.Cita;
import api_healthy_pet.Enums.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByVeterinario_IdUsuario(Long idUsuarioVeterinario);

    List<Cita> findByMascota_Cliente_IdUsuario(Long idUsuarioCliente);

    List<Cita> findByEstado(EstadoCita estado);
}
