package api_healthy_pet.Repositories;

import api_healthy_pet.Entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    // Busca el Owner (cliente) asociado a un usuario por su username.
    Optional<Owner> findByUser_Username(String username);

    // ¿El usuario logueado tiene ficha de cliente?
    boolean existsByUser_Username(String username);

    // Búsqueda / deduplicación por DNI.
    Optional<Owner> findByDni(String dni);

    boolean existsByDni(String dni);
}
