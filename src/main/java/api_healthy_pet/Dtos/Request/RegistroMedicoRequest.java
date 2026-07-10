package api_healthy_pet.DTOs.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistroMedicoRequest {

    @NotNull(message = "El id de la cita es obligatorio")
    private Long idCita;

    private String diagnostico;

    private String medicamentosRecetados;

    private String observaciones;
}
