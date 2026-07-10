package api_healthy_pet.Mappers;

import api_healthy_pet.DTOs.response.RecepcionistaResponse;
import api_healthy_pet.Entities.Recepcionista;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UsuarioMapper.class)
public interface RecepcionistaMapper {

    RecepcionistaResponse toResponse(Recepcionista recepcionista);
}
