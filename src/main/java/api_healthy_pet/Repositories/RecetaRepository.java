package api_healthy_pet.Repositories;

import api_healthy_pet.Entities.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta, Long> {

    List<Receta> findByRegistroMedico_IdRegistroMedico(Long idRegistroMedico);

    boolean existsByNumeroReceta(String numeroReceta);

    boolean existsByNumeroRecetaAndIdRecetaNot(String numeroReceta, Long idReceta);
}
