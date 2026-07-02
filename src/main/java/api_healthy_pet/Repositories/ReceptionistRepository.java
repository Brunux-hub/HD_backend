package api_healthy_pet.Repositories;

import api_healthy_pet.Entities.Receptionist;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReceptionistRepository extends JpaRepository<Receptionist, Long> {

    @Override
    @EntityGraph(attributePaths = "user")
    List<Receptionist> findAll();

    @Override
    @EntityGraph(attributePaths = "user")
    Optional<Receptionist> findById(Long idReceptionist);
}
