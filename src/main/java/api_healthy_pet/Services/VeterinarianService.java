package api_healthy_pet.Services;
import api_healthy_pet.Dtos.Request.VeterinarianRequest;
import api_healthy_pet.Dtos.Response.VeterinarianResponse;
import api_healthy_pet.Entities.Veterinarian;
import api_healthy_pet.Exceptions.VeterinarianException;
import api_healthy_pet.Mappers.VeterinarianMapper;
import api_healthy_pet.Repositories.VeterinarianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VeterinarianService {

    private final VeterinarianMapper veterinarianMapper;
    private final VeterinarianRepository veterinarianRepository;

    public VeterinarianResponse create (VeterinarianRequest request){
        return veterinarianMapper.toResponse(veterinarianRepository.save(veterinarianMapper.toEntity(request)));
    }

    public VeterinarianResponse findById (Long idVeterinarian){
        return veterinarianRepository.findById(idVeterinarian)
                .map(veterinarianMapper::toResponse)
                .orElseThrow(() -> new VeterinarianException("Veterinario no encontrado"));
    }

    public List<VeterinarianResponse> findAll(){
        return veterinarianRepository.findAll()
                .stream().map(veterinarianMapper::toResponse).toList();
    }

    public List<VeterinarianResponse> findAllAvailable(LocalDateTime date){
        return veterinarianRepository.findAvailableVeterinarians(date)
                .stream().map(veterinarianMapper::toResponse).toList();
    }

    public VeterinarianResponse updateById (Long idVeterinarian, VeterinarianRequest request){
        Veterinarian veterinarian = veterinarianRepository.findById(idVeterinarian)
                .orElseThrow(() -> new VeterinarianException("Veterinario no encontrado"));

        veterinarianMapper.updateEntityFromRequest(request, veterinarian);

        return veterinarianMapper.toResponse(veterinarianRepository.save(veterinarian));
    }

    public void deleteById (Long idVeterinarian) {
        if (!veterinarianRepository.existsById(idVeterinarian)){
            throw new VeterinarianException("Veterinario no encontrado");
        }
        veterinarianRepository.deleteById(idVeterinarian);
    }
}
