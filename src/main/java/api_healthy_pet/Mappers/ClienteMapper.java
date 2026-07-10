package api_healthy_pet.Mappers;

import api_healthy_pet.DTOs.response.ClienteResponse;
import api_healthy_pet.Entities.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UsuarioMapper.class)
public interface ClienteMapper {

    ClienteResponse toResponse(Cliente cliente);
}
