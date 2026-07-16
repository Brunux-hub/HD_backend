package api_healthy_pet.DTOs.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"idItemReceta", "idReceta", "medicamento", "cantidad", "dosis", "indicaciones"})
public class ItemRecetaResponse {

    private Long idItemReceta;
    private Long idReceta;
    private String medicamento;
    private String cantidad;
    private String dosis;
    private String indicaciones;
}
