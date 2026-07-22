package api_healthy_pet.DTOs.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"idReceta", "idRegistroMedico", "numeroReceta", "fechaEmision"})
public class RecetaResponse {

    private Long idReceta;
    private Long idRegistroMedico;
    private String numeroReceta;
    private LocalDateTime fechaEmision;
}
