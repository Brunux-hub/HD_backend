package api_healthy_pet.DTOs.response;

import api_healthy_pet.Enums.RolUsuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioResponse {

    private Long idUsuario;
    private String correo;
    private RolUsuario rol;
    private Boolean habilitado;
}
