package api_healthy_pet.DTOs.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecepcionistaResponse {

    private Long idUsuario;
    private String nombres;
    private String apellidos;
    private String telefono;
    private UsuarioResponse usuario;
}
