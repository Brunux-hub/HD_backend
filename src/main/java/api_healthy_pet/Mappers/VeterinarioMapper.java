package api_healthy_pet.Mappers;

import api_healthy_pet.Dtos.Request.VeterinarioRequest;
import api_healthy_pet.Dtos.Response.VeterinarioResponse;
import api_healthy_pet.Entities.Veterinario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VeterinarioMapper {

    Veterinario toEntity (VeterinarioRequest request);

    VeterinarioResponse toResponse (Veterinario veterinario);

    void updateEntityFromRequest (VeterinarioRequest request, @MappingTarget Veterinario veterinario);

}
