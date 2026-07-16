package api_healthy_pet.DTOs.response;

import api_healthy_pet.Enums.EstadoCita;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({
        "idCita",
        "motivo",
        "notas",
        "estado",
        "fechaProgramada",
        "idUsuarioRecepcionista",
        "idServicio",
        "idMascota",
        "idUsuarioVeterinario"
})
public class CitaResponse {

    private Long idCita;
    private String motivo;
    private String notas;
    private EstadoCita estado;
    private LocalDateTime fechaProgramada;
    private Long idUsuarioRecepcionista;
    private Long idServicio;
    private Long idMascota;
    private Long idUsuarioVeterinario;
}
