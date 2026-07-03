package api_healthy_pet.Mappers;

import api_healthy_pet.Dtos.Request.AppointmentRequest;
import api_healthy_pet.Dtos.Response.AppointmentResponse;
import api_healthy_pet.Entities.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ReceptionistMapper.class, PetMapper.class, VeterinarianMapper.class})
public interface AppointmentMapper {

    @Mapping(target = "pet.idPet", source = "idPet")
    @Mapping(target = "veterinarian.idVeterinarian", source = "idVeterinarian")
    Appointment toEntity (AppointmentRequest request);

    AppointmentResponse toResponse (Appointment appointment);

    void updateEntityFromRequest (AppointmentRequest request, @MappingTarget Appointment appointment);

}
