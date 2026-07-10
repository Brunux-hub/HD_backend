package api_healthy_pet.Repositories;

import api_healthy_pet.Entities.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {

    boolean existsByNumeroLicencia(String numeroLicencia);

    boolean existsByNumeroLicenciaAndIdUsuarioNot(String numeroLicencia, Long idUsuario);
}
