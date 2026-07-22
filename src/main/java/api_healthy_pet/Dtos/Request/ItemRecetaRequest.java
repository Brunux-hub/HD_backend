package api_healthy_pet.DTOs.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemRecetaRequest {

    @NotNull(message = "El id de la receta es obligatorio")
    private Long idReceta;

    @NotBlank(message = "El medicamento es obligatorio")
    private String medicamento;

    private String cantidad;

    private String dosis;

    private String indicaciones;
}
