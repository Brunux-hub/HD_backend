package api_healthy_pet.DTOs.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdministradorRequest {

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe tener un formato valido")
    private String correo;

    @Size(min = 6, max = 255, message = "La contrasenia debe tener al menos 6 caracteres")
    private String contrasenia;

    @NotBlank(message = "Los nombres son obligatorios")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    private Boolean habilitado;
}
