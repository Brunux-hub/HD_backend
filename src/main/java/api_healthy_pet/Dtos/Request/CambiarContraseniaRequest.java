package api_healthy_pet.DTOs.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CambiarContraseniaRequest {

    private String contraseniaActual;

    @NotBlank(message = "La nueva contrasenia es obligatoria")
    @Size(min = 6, max = 255, message = "La nueva contrasenia debe tener al menos 6 caracteres")
    private String nuevaContrasenia;
}
