package api_healthy_pet.DTOs.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser mayor a 0")
    private Double peso;

    private String medicamentosRecetados;

    private String observaciones;
}
