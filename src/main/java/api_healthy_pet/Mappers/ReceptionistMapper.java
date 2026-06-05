package api_healthy_pet.Mappers;

import api_healthy_pet.Dtos.Request.ReceptionistRequest;
import api_healthy_pet.Dtos.Response.ReceptionistResponse;
import api_healthy_pet.Entities.Receptionist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface ReceptionistMapper {

    @Mapping(target = "user.idUser", source = "idUser")
    Receptionist toEntity (ReceptionistRequest request);

    ReceptionistResponse toResponse (Receptionist receptionist);

    void updateEntityFromRequest (ReceptionistRequest request, @MappingTarget Receptionist receptionist);
}
