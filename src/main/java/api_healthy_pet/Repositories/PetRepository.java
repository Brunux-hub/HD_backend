package api_healthy_pet.Repositories;

import api_healthy_pet.Entities.Pet;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    @Override
    @EntityGraph(attributePaths = "owner")
    List<Pet> findAll();

    @Override
    @EntityGraph(attributePaths = "owner")
    Optional<Pet> findById(Long idPet);
}
