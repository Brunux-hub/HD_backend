package api_healthy_pet.DTOs.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"idMascota", "nombre", "especie", "raza", "sexo", "fechaNacimiento", "activo", "idUsuarioCliente"})
public class MascotaResponse {

    private Long idMascota;
    private String nombre;
    private String especie;
    private String raza;
    private String sexo;
    private LocalDate fechaNacimiento;
    private Boolean activo;
    private Long idUsuarioCliente;
}
