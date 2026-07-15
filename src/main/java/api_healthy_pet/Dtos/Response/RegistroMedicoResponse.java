package api_healthy_pet.DTOs.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RegistroMedicoResponse {

    private Long idRegistroMedico;
    private Long idCita;
    private LocalDateTime fecha;
    private String diagnostico;
    private Double peso;
    private String medicamentosRecetados;
    private String observaciones;
}
