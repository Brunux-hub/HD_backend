package api_healthy_pet.Mappers;

import api_healthy_pet.Dtos.Request.ClienteRequest;
import api_healthy_pet.Dtos.Response.ClienteResponse;
import api_healthy_pet.Entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toEntity (ClienteRequest request);

    ClienteResponse toResponse (Cliente cliente);

    void updateEntityFromRequest (ClienteRequest request, @MappingTarget Cliente cliente);
}
