package api_healthy_pet.Mappers;

import api_healthy_pet.Dtos.Request.MedicalHistoryRequest;
import api_healthy_pet.Dtos.Response.MedicalHistoryResponse;
import api_healthy_pet.Entities.MedicalHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {AppointmentMapper.class, ServiceMapper.class})
public interface MedicalHistoryMapper {

    @Mapping(target = "appointment.idAppointment", source = "idAppointment")
    @Mapping(target = "services.idService", source = "idService")
    MedicalHistory toEntity (MedicalHistoryRequest request);

    MedicalHistoryResponse toResponse (MedicalHistory medicalHistory);

    void updateEntityFromRequest (MedicalHistoryRequest request, @MappingTarget MedicalHistory medicalHistory);

}
