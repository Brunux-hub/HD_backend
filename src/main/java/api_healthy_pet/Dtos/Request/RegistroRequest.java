package api_healthy_pet.Dtos.Request;

import api_healthy_pet.Enums.EspecialidadVet;
import api_healthy_pet.Enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RegistroRequest(
        @NotNull Role role,
        @NotBlank @Email String email,
        @NotBlank String password,
        String nombre,
        String apellido,
        String telefono,
        List<EspecialidadVet> especialidades,
        String licencia,
        String direccion
) {
}
