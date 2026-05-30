package api_healthy_pet.Services;

import api_healthy_pet.Dtos.Request.VaccineRequest;
import api_healthy_pet.Dtos.Response.VaccineResponse;
import api_healthy_pet.Entities.Vaccine;
import api_healthy_pet.Exceptions.VaccineException;
import api_healthy_pet.Mappers.VaccineMapper;
import api_healthy_pet.Repositories.VaccineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccineService {

    private final VaccineMapper vaccineMapper;
    private final VaccineRepository vaccineRepository;

    public VaccineResponse create (VaccineRequest request){
        return vaccineMapper.toResponse(vaccineRepository.save(vaccineMapper.toEntity(request)));
    }

    public VaccineResponse findById (Long idVaccine){
        return vaccineRepository.findById(idVaccine)
                .map(vaccineMapper::toResponse)
                .orElseThrow(() -> new VaccineException("Vacuna no encontrada"));
    }

    public List<VaccineResponse> findAll(){
        return vaccineRepository.findAll()
                .stream().map(vaccineMapper::toResponse).toList();
    }

    public VaccineResponse updateById (Long idVaccine, VaccineRequest request){
        Vaccine vaccine = vaccineRepository.findById(idVaccine)
                .orElseThrow(() -> new VaccineException("Vacuna no encontrada"));

        vaccineMapper.updateEntityFromRequest(request, vaccine);

        return vaccineMapper.toResponse(vaccineRepository.save(vaccine));
    }

    public void deleteById (Long idVaccine) {
        if (!vaccineRepository.existsById(idVaccine)){
            throw new VaccineException("Vacuna no encontrada");
        }
        vaccineRepository.deleteById(idVaccine);
    }

}
