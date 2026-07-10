package api_healthy_pet.Repositories;

import api_healthy_pet.Entities.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {

    List<Tratamiento> findByRegistroMedico_IdRegistroMedico(Long idRegistroMedico);
}
