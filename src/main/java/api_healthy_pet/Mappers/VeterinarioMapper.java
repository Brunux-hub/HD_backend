package api_healthy_pet.Mappers;

import api_healthy_pet.DTOs.response.VeterinarioResponse;
import api_healthy_pet.Entities.Veterinario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UsuarioMapper.class)
public interface VeterinarioMapper {

    VeterinarioResponse toResponse(Veterinario veterinario);
}
