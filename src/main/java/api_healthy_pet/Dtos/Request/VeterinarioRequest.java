package api_healthy_pet.DTOs.request;

import api_healthy_pet.Enums.EspecialidadVeterinario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class VeterinarioRequest {

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe tener un formato valido")
    private String correo;

    @Size(min = 6, max = 255, message = "La contrasenia debe tener al menos 6 caracteres")
    private String contrasenia;

    @NotBlank(message = "Los nombres son obligatorios")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    @Size(max = 30, message = "El telefono no puede superar 30 caracteres")
    private String telefono;

    @NotBlank(message = "El numero de licencia es obligatorio")
    private String numeroLicencia;

    @NotNull(message = "Debe seleccionar al menos una especialidad")
    @Size(min = 1, max = 2, message = "El veterinario debe tener entre 1 y 2 especialidades")
    private Set<EspecialidadVeterinario> especialidades;

    private Boolean habilitado;
}
