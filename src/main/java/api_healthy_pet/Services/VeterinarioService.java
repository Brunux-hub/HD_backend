package api_healthy_pet.Services;

import api_healthy_pet.Dtos.Request.VeterinarioRequest;
import api_healthy_pet.Dtos.Response.VeterinarioResponse;
import api_healthy_pet.Entities.Veterinario;
import api_healthy_pet.Exceptions.VeterinarioException;
import api_healthy_pet.Mappers.VeterinarioMapper;
import api_healthy_pet.Repositories.VeterinarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VeterinarioService {

    private final VeterinarioMapper veterinarioMapper;
    private final VeterinarioRepository veterinarioRepository;

    public VeterinarioResponse create (VeterinarioRequest request){
        return veterinarioMapper.toResponse(veterinarioRepository.save(veterinarioMapper.toEntity(request)));
    }

    public VeterinarioResponse findById (Long userId){
        return veterinarioRepository.findById(userId)
                .map(veterinarioMapper::toResponse)
                .orElseThrow(() -> new VeterinarioException("Veterinario no encontrado"));
    }

    public List<VeterinarioResponse> findAll(){
        return veterinarioRepository.findAll()
                .stream().map(veterinarioMapper::toResponse).toList();
    }

    public List<VeterinarioResponse> findAllAvailable(LocalDateTime date){
        return veterinarioRepository.findAvailableVeterinarios(date)
                .stream().map(veterinarioMapper::toResponse).toList();
    }

    public VeterinarioResponse updateById (Long userId, VeterinarioRequest request){
        Veterinario veterinario = veterinarioRepository.findById(userId)
                .orElseThrow(() -> new VeterinarioException("Veterinario no encontrado"));

        veterinarioMapper.updateEntityFromRequest(request, veterinario);

        Veterinario updatedVeterinario = veterinarioRepository.save(veterinario);

        return veterinarioRepository.findById(updatedVeterinario.getUserId())
                .map(veterinarioMapper::toResponse)
                .orElseThrow(() -> new VeterinarioException("Veterinario no encontrado"));
    }

    public void deleteById (Long userId) {
        if (!veterinarioRepository.existsById(userId)){
            throw new VeterinarioException("Veterinario no encontrado");
        }
        veterinarioRepository.deleteById(userId);
    }
}
