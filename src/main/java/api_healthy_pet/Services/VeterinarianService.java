package api_healthy_pet.Services;

import api_healthy_pet.Dtos.Request.VeterinarianRequest;
import api_healthy_pet.Dtos.Response.VeterinarianResponse;
import api_healthy_pet.Entities.User;
import api_healthy_pet.Entities.Veterinarian;
import api_healthy_pet.Enums.UserType;
import api_healthy_pet.Exceptions.UserException;
import api_healthy_pet.Exceptions.VeterinarianException;
import api_healthy_pet.Mappers.VeterinarianMapper;
import api_healthy_pet.Repositories.UserRepository;
import api_healthy_pet.Repositories.VeterinarianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VeterinarianService {

    private final VeterinarianMapper veterinarianMapper;
    private final VeterinarianRepository veterinarianRepository;
    private final UserRepository userRepository;

    @Transactional
    public VeterinarianResponse create (VeterinarianRequest request){
        User user = userRepository.findById(request.idUser())
                .orElseThrow(() -> new UserException("Usuario no encontrado"));

        if (user.getType() == UserType.WORKER) {
            user.setType(UserType.VETERINARIAN);
            userRepository.save(user);
        } else if (user.getType() != UserType.VETERINARIAN) {
            throw new VeterinarianException(
                    "El usuario debe tener tipo VETERINARIAN para crear un perfil de veterinario");
        }

        Veterinarian veterinarian = veterinarianMapper.toEntity(request);
        veterinarian.setUser(user);
        return veterinarianMapper.toResponse(veterinarianRepository.save(veterinarian));
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

        Veterinarian updatedVeterinarian = veterinarianRepository.save(veterinarian);

        return veterinarianRepository.findById(updatedVeterinarian.getIdVeterinarian())
                .map(veterinarianMapper::toResponse)
                .orElseThrow(() -> new VeterinarianException("Veterinario no encontrado"));
    }

    public void deleteById (Long idVeterinarian) {
        if (!veterinarianRepository.existsById(idVeterinarian)){
            throw new VeterinarianException("Veterinario no encontrado");
        }
        veterinarianRepository.deleteById(idVeterinarian);
    }
}
