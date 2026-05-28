package api_healthy_pet.Services;

import api_healthy_pet.Dtos.Request.ReceptionistRequest;
import api_healthy_pet.Dtos.Response.ReceptionistResponse;
import api_healthy_pet.Entities.Receptionist;
import api_healthy_pet.Exceptions.ReceptionistException;
import api_healthy_pet.Mappers.ReceptionistMapper;
import api_healthy_pet.Repositories.ReceptionistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceptionistService {

    private final ReceptionistMapper receptionistMapper;
    private final ReceptionistRepository receptionistRepository;

    public ReceptionistResponse create (ReceptionistRequest request){
        return receptionistMapper.toResponse(receptionistRepository.save(receptionistMapper.toEntity(request)));
    }

    public ReceptionistResponse findById (Long idUser){
        return receptionistRepository.findById(idUser)
                .map(receptionistMapper::toResponse)
                .orElseThrow(() -> new ReceptionistException("Receptionista no encontrado"));
    }

    public List<ReceptionistResponse> findAll(){
        return receptionistRepository.findAll()
                .stream().map(receptionistMapper::toResponse).toList();
    }

    public ReceptionistResponse updateById (Long idReceptionist, ReceptionistRequest request){
        Receptionist receptionist = receptionistRepository.findById(idReceptionist)
                .orElseThrow(() -> new ReceptionistException("Receptionista no encontrado"));

        receptionistMapper.updateEntityFromRequest(request, receptionist);

        return receptionistMapper.toResponse(receptionistRepository.save(receptionist));
    }

    public void deleteById (Long idReceptionist) {
        if (!receptionistRepository.existsById(idReceptionist)){
            throw new ReceptionistException("Receptionista no encontrado");
        }
        receptionistRepository.deleteById(idReceptionist);
    }

}
