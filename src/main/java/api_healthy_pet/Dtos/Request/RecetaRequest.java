package api_healthy_pet.DTOs.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecetaRequest {

    @NotNull(message = "El id del registro medico es obligatorio")
    private Long idRegistroMedico;
}
