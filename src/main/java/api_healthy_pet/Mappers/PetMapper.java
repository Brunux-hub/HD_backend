package api_healthy_pet.Mappers;

import api_healthy_pet.Dtos.Request.PetRequest;
import api_healthy_pet.Dtos.Response.PetResponse;
import api_healthy_pet.Entities.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = OwnerMapper.class)
public interface PetMapper {

    @Mapping(target = "owner.idOwner", source = "idOwner")
    Pet toEntity(PetRequest request);

    PetResponse toResponse (Pet pet);

    void updateEntityFromRequest (PetRequest request, @MappingTarget Pet pet);

}
