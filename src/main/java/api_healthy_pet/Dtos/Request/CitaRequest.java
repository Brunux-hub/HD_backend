package api_healthy_pet.DTOs.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CitaRequest {

    @NotBlank(message = "El motivo es obligatorio")
    private String motivo;

    private String notas;

    @NotNull(message = "La fecha programada es obligatoria")
    @Future(message = "La fecha programada debe ser futura")
    private LocalDateTime fechaProgramada;

    @NotNull(message = "El id de recepcionista es obligatorio")
    private Long idUsuarioRecepcionista;

    @NotNull(message = "El id del servicio es obligatorio")
    private Long idServicio;

    @NotNull(message = "El id de la mascota es obligatorio")
    private Long idMascota;

    @NotNull(message = "El id del veterinario es obligatorio")
    private Long idUsuarioVeterinario;
}
