package api_healthy_pet.Mappers;

import api_healthy_pet.DTOs.response.MascotaResumenResponse;
import api_healthy_pet.DTOs.response.RegistroMedicoResponse;
import api_healthy_pet.Entities.RegistroMedico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegistroMedicoMapper {

    @Mapping(target = "idCita", source = "cita.idCita")
    @Mapping(target = "mascota.idMascota", source = "cita.mascota.idMascota")
    @Mapping(target = "mascota.nombre", source = "cita.mascota.nombre")
    RegistroMedicoResponse toResponse(RegistroMedico registroMedico);
}
