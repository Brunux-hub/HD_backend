package api_healthy_pet.Services;

import api_healthy_pet.Dtos.Request.VaccinationRequest;
import api_healthy_pet.Dtos.Response.VaccinationResponse;
import api_healthy_pet.Entities.Vaccination;
import api_healthy_pet.Exceptions.VaccinationException;
import api_healthy_pet.Mappers.VaccinationMapper;
import api_healthy_pet.Repositories.VaccinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccinationService {

    private final VaccinationMapper vaccinationMapper;
    private final VaccinationRepository vaccinationRepository;

    public VaccinationResponse create (VaccinationRequest request){
        return vaccinationMapper.toResponse(vaccinationRepository.save(vaccinationMapper.toEntity(request)));
    }

    public VaccinationResponse findById (Long idVaccination){
        return vaccinationRepository.findById(idVaccination)
                .map(vaccinationMapper::toResponse)
                .orElseThrow(() -> new VaccinationException("Vacunacion no encontrada"));
    }

    public List<VaccinationResponse> findAll(){
        return vaccinationRepository.findAll()
                .stream().map(vaccinationMapper::toResponse).toList();
    }

    public VaccinationResponse updateById (Long idVaccination, VaccinationRequest request){
        Vaccination vaccination = vaccinationRepository.findById(idVaccination)
                .orElseThrow(() -> new VaccinationException("Vacunacion no encontrada"));

        vaccinationMapper.updateEntityFromRequest(request, vaccination);

        return vaccinationMapper.toResponse(vaccinationRepository.save(vaccination));
    }

    public void deleteById (Long idVaccination) {
        if (!vaccinationRepository.existsById(idVaccination)){
            throw new VaccinationException("Vacunacion no encontrada");
        }
        vaccinationRepository.deleteById(idVaccination);
    }
}
