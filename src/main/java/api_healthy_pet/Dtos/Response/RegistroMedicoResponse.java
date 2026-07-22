package api_healthy_pet.DTOs.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"idRegistroMedico", "idCita", "fecha", "diagnostico", "peso", "observaciones", "mascota"})
public class RegistroMedicoResponse {

    private Long idRegistroMedico;
    private Long idCita;
    private LocalDateTime fecha;
    private String diagnostico;
    private Double peso;
    private String observaciones;
    private MascotaResumenResponse mascota;
}
