package api_healthy_pet.DTOs.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"idServicio", "nombre", "descripcion", "precio", "activo"})
public class ServicioResponse {

    private Long idServicio;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Boolean activo;
}
