package api_healthy_pet.DTOs.request;

import api_healthy_pet.Enums.RolUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioRequest {

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe tener un formato valido")
    private String correo;

    @Size(min = 6, max = 255, message = "La contrasenia debe tener al menos 6 caracteres")
    private String contrasenia;

    @NotNull(message = "El rol es obligatorio")
    private RolUsuario rol;

    private Boolean habilitado;
}
