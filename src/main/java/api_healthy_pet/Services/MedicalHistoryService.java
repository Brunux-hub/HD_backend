package api_healthy_pet.Services;

import api_healthy_pet.Dtos.Request.MedicalHistoryRequest;
import api_healthy_pet.Dtos.Response.MedicalHistoryResponse;
import api_healthy_pet.Entities.MedicalHistory;
import api_healthy_pet.Exceptions.MedicalHistoryException;
import api_healthy_pet.Mappers.MedicalHistoryMapper;
import api_healthy_pet.Repositories.MedicalHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalHistoryService {

    private final MedicalHistoryMapper medicalHistoryMapper;
    private final MedicalHistoryRepository medicalHistoryRepository;

    public MedicalHistoryResponse create (MedicalHistoryRequest request){
        return medicalHistoryMapper.toResponse(medicalHistoryRepository.save(medicalHistoryMapper.toEntity(request)));
    }

    public MedicalHistoryResponse findById (Long idMedicalHistory){
        return medicalHistoryRepository.findById(idMedicalHistory)
                .map(medicalHistoryMapper::toResponse)
                .orElseThrow(() -> new MedicalHistoryException("Historial medico no encontrado"));
    }

    public List<MedicalHistoryResponse> findAll(){
        return medicalHistoryRepository.findAll()
                .stream().map(medicalHistoryMapper::toResponse).toList();
    }

    public MedicalHistoryResponse updateById (Long idMedicalHistory, MedicalHistoryRequest request){
        MedicalHistory medicalHistory = medicalHistoryRepository.findById(idMedicalHistory)
                .orElseThrow(() -> new MedicalHistoryException("Historial medico no encontrado"));

        medicalHistoryMapper.updateEntityFromRequest(request, medicalHistory);

        return medicalHistoryMapper.toResponse(medicalHistoryRepository.save(medicalHistory));
    }

    public void deleteById (Long idMedicalHistory) {
        if (!medicalHistoryRepository.existsById(idMedicalHistory)){
            throw new MedicalHistoryException("Historial medico no encontrado");
        }
        medicalHistoryRepository.deleteById(idMedicalHistory);
    }

}
