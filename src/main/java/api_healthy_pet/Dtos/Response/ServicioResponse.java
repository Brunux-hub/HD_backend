package api_healthy_pet.DTOs.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ServicioResponse {

    private Long idServicio;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Boolean activo;
}
