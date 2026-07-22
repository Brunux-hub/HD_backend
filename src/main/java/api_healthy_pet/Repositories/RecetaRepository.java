package api_healthy_pet.Repositories;

import api_healthy_pet.Entities.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta, Long> {

    List<Receta> findByRegistroMedico_IdRegistroMedico(Long idRegistroMedico);

    boolean existsByNumeroReceta(String numeroReceta);

    boolean existsByNumeroRecetaAndIdRecetaNot(String numeroReceta, Long idReceta);

    @Query("select r.numeroReceta from Receta r where r.numeroReceta is not null")
    List<String> findAllNumeroReceta();
}
