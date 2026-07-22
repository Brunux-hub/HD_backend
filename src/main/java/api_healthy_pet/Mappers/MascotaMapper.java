package api_healthy_pet.Mappers;

import api_healthy_pet.DTOs.response.MascotaResponse;
import api_healthy_pet.Entities.Mascota;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MascotaMapper {

    @Mapping(target = "idUsuarioCliente", source = "cliente.idUsuario")
    MascotaResponse toResponse(Mascota mascota);
}
