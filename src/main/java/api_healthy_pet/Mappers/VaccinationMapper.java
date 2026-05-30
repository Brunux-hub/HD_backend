package api_healthy_pet.Mappers;

import api_healthy_pet.Dtos.Request.VaccinationRequest;
import api_healthy_pet.Dtos.Response.VaccinationResponse;
import api_healthy_pet.Entities.Vaccination;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {MedicalHistoryMapper.class, VaccineMapper.class})
public interface VaccinationMapper {

    @Mapping(target = "medicalHistory.idMedicalHistory", source = "idMedicalHistory")
    @Mapping(target = "vaccine.idVaccine", source = "idVaccine")
    Vaccination toEntity (VaccinationRequest request);

    VaccinationResponse toResponse (Vaccination vaccination);

    void updateEntityFromRequest (VaccinationRequest request, @MappingTarget Vaccination vaccination);

}
