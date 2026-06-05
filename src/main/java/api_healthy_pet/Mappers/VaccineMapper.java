package api_healthy_pet.Mappers;

import api_healthy_pet.Dtos.Request.VaccineRequest;
import api_healthy_pet.Dtos.Response.VaccineResponse;
import api_healthy_pet.Entities.Vaccine;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VaccineMapper {

    Vaccine toEntity (VaccineRequest request);

    VaccineResponse toResponse (Vaccine vaccine);

    void updateEntityFromRequest (VaccineRequest request, @MappingTarget Vaccine vaccine);


}
