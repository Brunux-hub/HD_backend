package api_healthy_pet.DTOs.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TratamientoRequest {

    @NotNull(message = "El id del registro medico es obligatorio")
    private Long idRegistroMedico;

    @NotBlank(message = "El medicamento es obligatorio")
    private String medicamento;

    private String dosis;

    private String frecuencia;

    private String duracion;

    private String indicaciones;
}
