package api_healthy_pet.Mappers;

import api_healthy_pet.Dtos.Request.VeterinarianRequest;
import api_healthy_pet.Dtos.Response.VeterinarianResponse;
import api_healthy_pet.Entities.Veterinarian;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface VeterinarianMapper {

    @Mapping(target = "user.idUser", source = "idUser")
    Veterinarian toEntity (VeterinarianRequest request);

    @Mapping(target = "userResponse", source = "user")
    VeterinarianResponse toResponse (Veterinarian veterinarian);

    void updateEntityFromRequest (VeterinarianRequest request, @MappingTarget Veterinarian veterinarian);

}
