package api_healthy_pet.Mappers;

import api_healthy_pet.DTOs.response.ItemRecetaResponse;
import api_healthy_pet.Entities.ItemReceta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemRecetaMapper {

    @Mapping(target = "idReceta", source = "receta.idReceta")
    ItemRecetaResponse toResponse(ItemReceta itemReceta);
}
