package api_healthy_pet.DTOs.response;

import api_healthy_pet.Enums.EspecialidadVeterinario;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({
        "idUsuario",
        "nombres",
        "apellidos",
        "telefono",
        "numeroLicencia",
        "especialidades",
        "usuario"
})
public class VeterinarioResponse {

    private Long idUsuario;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String numeroLicencia;
    private Set<EspecialidadVeterinario> especialidades;
    private UsuarioResponse usuario;
}
