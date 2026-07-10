package api_healthy_pet.Mappers;

import api_healthy_pet.DTOs.response.TratamientoResponse;
import api_healthy_pet.Entities.Tratamiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TratamientoMapper {

    @Mapping(target = "idRegistroMedico", source = "registroMedico.idRegistroMedico")
    TratamientoResponse toResponse(Tratamiento tratamiento);
}
