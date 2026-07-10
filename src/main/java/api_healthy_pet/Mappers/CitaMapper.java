package api_healthy_pet.Mappers;

import api_healthy_pet.DTOs.response.CitaResponse;
import api_healthy_pet.Entities.Cita;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CitaMapper {

    @Mapping(target = "idUsuarioRecepcionista", source = "recepcionista.idUsuario")
    @Mapping(target = "idServicio", source = "servicio.idServicio")
    @Mapping(target = "idMascota", source = "mascota.idMascota")
    @Mapping(target = "idUsuarioVeterinario", source = "veterinario.idUsuario")
    CitaResponse toResponse(Cita cita);
}
