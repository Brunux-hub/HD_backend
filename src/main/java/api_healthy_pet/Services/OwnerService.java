package api_healthy_pet.Services;

import api_healthy_pet.Dtos.Request.OwnerRequest;
import api_healthy_pet.Dtos.Response.OwnerResponse;
import api_healthy_pet.Entities.Owner;
import api_healthy_pet.Exceptions.OwnerException;
import api_healthy_pet.Mappers.OwnerMapper;
import api_healthy_pet.Repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerMapper ownerMapper;
    private final OwnerRepository ownerRepository;

    public OwnerResponse create (OwnerRequest request){
        return ownerMapper.toResponse(ownerRepository.save(ownerMapper.toEntity(request)));
    }

    public OwnerResponse findById (Long idOwner){
        return ownerRepository.findById(idOwner)
                .map(ownerMapper::toResponse)
                .orElseThrow(() -> new OwnerException("Dueño no encontrado"));
    }

    public List<OwnerResponse> findAll(){
        return ownerRepository.findAll()
                .stream().map(ownerMapper::toResponse).toList();
    }

    public OwnerResponse updateById (Long idOwner, OwnerRequest request){
        Owner owner = ownerRepository.findById(idOwner)
                .orElseThrow(() -> new OwnerException("Dueño no encontrado"));

        ownerMapper.updateEntityFromRequest(request, owner);

        return ownerMapper.toResponse(ownerRepository.save(owner));
    }

    public void deleteById (Long idOwner) {
        if (!ownerRepository.existsById(idOwner)){
            throw new OwnerException("Dueño no encontrado");
        }
        ownerRepository.deleteById(idOwner);
    }

}
