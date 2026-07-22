package api_healthy_pet.Mappers;

import api_healthy_pet.DTOs.response.UsuarioResponse;
import api_healthy_pet.Entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioResponse toResponse(Usuario usuario);
}
