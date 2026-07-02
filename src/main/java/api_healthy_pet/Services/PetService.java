package api_healthy_pet.Services;

import api_healthy_pet.Dtos.Request.PetRequest;
import api_healthy_pet.Dtos.Response.PetResponse;
import api_healthy_pet.Entities.Pet;
import api_healthy_pet.Exceptions.PetException;
import api_healthy_pet.Mappers.PetMapper;
import api_healthy_pet.Repositories.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetMapper petMapper;
    private final PetRepository petRepository;

    public PetResponse create (PetRequest request){
        return petMapper.toResponse(petRepository.save(petMapper.toEntity(request)));
    }

    public PetResponse findById (Long idPet){
        return petRepository.findById(idPet)
                .map(petMapper::toResponse)
                .orElseThrow(() -> new PetException("Mascota no encontrada"));
    }

    public List<PetResponse> findAll(){
        return petRepository.findAll()
                .stream().map(petMapper::toResponse).toList();
    }

    public PetResponse updateById (Long idPet, PetRequest request){
        Pet pet = petRepository.findById(idPet)
                .orElseThrow(() -> new PetException("Mascota no encontrada"));

        petMapper.updateEntityFromRequest(request, pet);

        Pet updatedPet = petRepository.save(pet);

        return petRepository.findById(updatedPet.getIdPet())
                .map(petMapper::toResponse)
                .orElseThrow(() -> new PetException("Mascota no encontrada"));
    }

    public void deleteById (Long idPet) {
        if (!petRepository.existsById(idPet)){
            throw new PetException("Mascota no encontrada");
        }
        petRepository.deleteById(idPet);
    }

}
