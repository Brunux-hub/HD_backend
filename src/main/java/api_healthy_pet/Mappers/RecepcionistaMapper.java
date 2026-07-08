package api_healthy_pet.Mappers;

import api_healthy_pet.Dtos.Request.RecepcionistaRequest;
import api_healthy_pet.Dtos.Response.RecepcionistaResponse;
import api_healthy_pet.Entities.Recepcionista;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RecepcionistaMapper {

    Recepcionista toEntity (RecepcionistaRequest request);

    RecepcionistaResponse toResponse (Recepcionista recepcionista);

    void updateEntityFromRequest (RecepcionistaRequest request, @MappingTarget Recepcionista recepcionista);
}
