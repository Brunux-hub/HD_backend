package api_healthy_pet.Mappers;

import api_healthy_pet.DTOs.response.RecetaResponse;
import api_healthy_pet.Entities.Receta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecetaMapper {

    @Mapping(target = "idRegistroMedico", source = "registroMedico.idRegistroMedico")
    RecetaResponse toResponse(Receta receta);
}
