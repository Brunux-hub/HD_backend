package api_healthy_pet.DTOs.response;

import api_healthy_pet.Enums.RolUsuario;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"idUsuario", "correo", "rol", "habilitado"})
public class UsuarioResponse {

    private Long idUsuario;
    private String correo;
    private RolUsuario rol;
    private Boolean habilitado;
}
