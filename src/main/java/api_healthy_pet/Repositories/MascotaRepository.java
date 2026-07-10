package api_healthy_pet.Repositories;

import api_healthy_pet.Entities.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    List<Mascota> findByCliente_IdUsuario(Long idUsuarioCliente);

    List<Mascota> findByCliente_IdUsuarioAndActivoTrue(Long idUsuarioCliente);
}
