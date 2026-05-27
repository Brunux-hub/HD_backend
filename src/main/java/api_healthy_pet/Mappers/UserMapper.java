package api_healthy_pet.Mappers;

import api_healthy_pet.Dtos.Request.UserRequest;
import api_healthy_pet.Dtos.Response.UserResponse;
import api_healthy_pet.Entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity (UserRequest request);

    UserResponse toResponse (User user);

    void updateEntityFromRequest (UserRequest request, @MappingTarget User user);
}
