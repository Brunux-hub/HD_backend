package api_healthy_pet.DTOs.request;

import api_healthy_pet.Enums.EstadoCita;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CitaEstadoRequest {

    @NotNull(message = "El estado es obligatorio")
    private EstadoCita estado;
}
