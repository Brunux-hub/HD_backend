package api_healthy_pet.DTOs.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemRecetaResponse {

    private Long idItemReceta;
    private Long idReceta;
    private String medicamento;
    private String cantidad;
    private String dosis;
    private String indicaciones;
}
