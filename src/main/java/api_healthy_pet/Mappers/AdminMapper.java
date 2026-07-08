package api_healthy_pet.Mappers;

import api_healthy_pet.Dtos.Response.AdminResponse;
import api_healthy_pet.Entities.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface AdminMapper {

    AdminResponse toResponse (Admin admin);
}
