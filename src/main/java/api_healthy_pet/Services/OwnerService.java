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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerMapper ownerMapper;
    private final OwnerRepository ownerRepository;

    // Alta de cliente desde el staff: sin DNI duplicado.
    public OwnerResponse create (OwnerRequest request){
        if (ownerRepository.existsByDni(request.dni())) {
            throw new OwnerException("Ya existe un cliente registrado con ese DNI");
        }
        return ownerMapper.toResponse(ownerRepository.save(ownerMapper.toEntity(request)));
    }

    // Búsqueda por DNI (para verificar antes de crear).
    public Optional<OwnerResponse> findByDni(String dni){
        return ownerRepository.findByDni(dni).map(ownerMapper::toResponse);
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

    // Devuelve el Owner del usuario logueado (por username). Vacío si el usuario
    // no es cliente (p.ej. staff con ficha de vet/recepcionista).
    public Optional<OwnerResponse> findByUsername(String username){
        return ownerRepository.findByUser_Username(username).map(ownerMapper::toResponse);
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
