package api_healthy_pet.DTOs.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"idUsuario", "nombres", "apellidos", "telefono", "usuario"})
public class RecepcionistaResponse {

    private Long idUsuario;
    private String nombres;
    private String apellidos;
    private String telefono;
    private UsuarioResponse usuario;
}
