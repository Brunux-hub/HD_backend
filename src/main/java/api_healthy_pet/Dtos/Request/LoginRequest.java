package api_healthy_pet.DTOs.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe tener un formato valido")
    private String correo;

    @NotBlank(message = "La contrasenia es obligatoria")
    private String contrasenia;
}
