package api_healthy_pet.Mappers;

import api_healthy_pet.DTOs.response.RegistroMedicoResponse;
import api_healthy_pet.Entities.RegistroMedico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegistroMedicoMapper {

    @Mapping(target = "idCita", source = "cita.idCita")
    RegistroMedicoResponse toResponse(RegistroMedico registroMedico);
}
