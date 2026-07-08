package api_healthy_pet.Services;

import api_healthy_pet.Dtos.Request.RecepcionistaRequest;
import api_healthy_pet.Dtos.Response.RecepcionistaResponse;
import api_healthy_pet.Entities.Recepcionista;
import api_healthy_pet.Exceptions.RecepcionistaException;
import api_healthy_pet.Mappers.RecepcionistaMapper;
import api_healthy_pet.Repositories.RecepcionistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecepcionistaService {

    private final RecepcionistaMapper recepcionistaMapper;
    private final RecepcionistaRepository recepcionistaRepository;

    public RecepcionistaResponse create (RecepcionistaRequest request){
        return recepcionistaMapper.toResponse(recepcionistaRepository.save(recepcionistaMapper.toEntity(request)));
    }

    public RecepcionistaResponse findById (Long userId){
        return recepcionistaRepository.findById(userId)
                .map(recepcionistaMapper::toResponse)
                .orElseThrow(() -> new RecepcionistaException("Recepcionista no encontrado"));
    }

    public List<RecepcionistaResponse> findAll(){
        return recepcionistaRepository.findAll()
                .stream().map(recepcionistaMapper::toResponse).toList();
    }

    public RecepcionistaResponse updateById (Long userId, RecepcionistaRequest request){
        Recepcionista recepcionista = recepcionistaRepository.findById(userId)
                .orElseThrow(() -> new RecepcionistaException("Recepcionista no encontrado"));

        recepcionistaMapper.updateEntityFromRequest(request, recepcionista);

        Recepcionista updatedRecepcionista = recepcionistaRepository.save(recepcionista);

        return recepcionistaRepository.findById(updatedRecepcionista.getUserId())
                .map(recepcionistaMapper::toResponse)
                .orElseThrow(() -> new RecepcionistaException("Recepcionista no encontrado"));
    }

    public void deleteById (Long userId) {
        if (!recepcionistaRepository.existsById(userId)){
            throw new RecepcionistaException("Recepcionista no encontrado");
        }
        recepcionistaRepository.deleteById(userId);
    }

}
