package api_healthy_pet.DTOs.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MascotaRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La especie es obligatoria")
    private String especie;

    private String raza;

    private String sexo;

    private LocalDate fechaNacimiento;

    @NotNull(message = "El id del cliente es obligatorio")
    private Long idUsuarioCliente;

    private Boolean activo;
}
