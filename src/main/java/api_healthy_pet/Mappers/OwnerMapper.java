package api_healthy_pet.Mappers;

import api_healthy_pet.Dtos.Request.OwnerRequest;
import api_healthy_pet.Dtos.Response.OwnerResponse;
import api_healthy_pet.Entities.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    Owner toEntity (OwnerRequest request);

    OwnerResponse toResponse (Owner owner);

    void updateEntityFromRequest (OwnerRequest request, @MappingTarget Owner owner);

}
