package api_healthy_pet.Mappers;

import api_healthy_pet.DTOs.response.AdministradorResponse;
import api_healthy_pet.Entities.Administrador;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UsuarioMapper.class)
public interface AdministradorMapper {

    AdministradorResponse toResponse(Administrador administrador);
}
