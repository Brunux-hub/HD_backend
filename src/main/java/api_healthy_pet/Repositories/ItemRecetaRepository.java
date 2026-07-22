package api_healthy_pet.Repositories;

import api_healthy_pet.Entities.ItemReceta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRecetaRepository extends JpaRepository<ItemReceta, Long> {

    List<ItemReceta> findByReceta_IdReceta(Long idReceta);
}
