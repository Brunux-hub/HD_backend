package api_healthy_pet.Mappers;

import api_healthy_pet.Dtos.Request.PetRequest;
import api_healthy_pet.Dtos.Response.PetResponse;
import api_healthy_pet.Entities.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = ClienteMapper.class)
public interface PetMapper {

    @Mapping(target = "cliente.userId", source = "clienteId")
    Pet toEntity(PetRequest request);

    @Mapping(target = "petGender", source = "sex")
    PetResponse toResponse (Pet pet);

    @Mapping(target = "cliente.userId", source = "clienteId")
    void updateEntityFromRequest (PetRequest request, @MappingTarget Pet pet);

}
