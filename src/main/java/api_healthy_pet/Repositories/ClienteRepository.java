package api_healthy_pet.Repositories;

import api_healthy_pet.Entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByUsuario_Correo(String correo);

    boolean existsByDni(String dni);

    boolean existsByDniAndIdUsuarioNot(String dni, Long idUsuario);
}
