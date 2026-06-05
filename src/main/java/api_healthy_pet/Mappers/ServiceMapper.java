package api_healthy_pet.Mappers;

import api_healthy_pet.Dtos.Request.ServiceRequest;
import api_healthy_pet.Dtos.Response.ServiceResponse;
import api_healthy_pet.Entities.Services;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    Services toEntity (ServiceRequest request);

    ServiceResponse toResponse (Services service);

    void updateEntityFromRequest (ServiceRequest request, @MappingTarget Services service);

}
