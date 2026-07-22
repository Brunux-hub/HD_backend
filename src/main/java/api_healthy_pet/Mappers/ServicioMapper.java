package api_healthy_pet.Mappers;

import api_healthy_pet.DTOs.response.ServicioResponse;
import api_healthy_pet.Entities.Servicio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServicioMapper {

    ServicioResponse toResponse(Servicio servicio);
}
