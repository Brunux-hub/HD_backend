package api_healthy_pet.Services;

import api_healthy_pet.Dtos.Request.ReceptionistRequest;
import api_healthy_pet.Dtos.Response.ReceptionistResponse;
import api_healthy_pet.Entities.Receptionist;
import api_healthy_pet.Entities.User;
import api_healthy_pet.Enums.UserType;
import api_healthy_pet.Exceptions.ReceptionistException;
import api_healthy_pet.Exceptions.UserException;
import api_healthy_pet.Mappers.ReceptionistMapper;
import api_healthy_pet.Repositories.ReceptionistRepository;
import api_healthy_pet.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceptionistService {

    private final ReceptionistMapper receptionistMapper;
    private final ReceptionistRepository receptionistRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReceptionistResponse create (ReceptionistRequest request){
        User user = userRepository.findById(request.idUser())
                .orElseThrow(() -> new UserException("Usuario no encontrado"));

        if (user.getType() == UserType.WORKER) {
            user.setType(UserType.RECEPTIONIST);
            userRepository.save(user);
        } else if (user.getType() != UserType.RECEPTIONIST) {
            throw new ReceptionistException(
                    "El usuario debe tener tipo RECEPTIONIST para crear un perfil de recepcionista");
        }

        Receptionist receptionist = receptionistMapper.toEntity(request);
        receptionist.setUser(user);
        return receptionistMapper.toResponse(receptionistRepository.save(receptionist));
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

        Receptionist updatedReceptionist = receptionistRepository.save(receptionist);

        return receptionistRepository.findById(updatedReceptionist.getIdReceptionist())
                .map(receptionistMapper::toResponse)
                .orElseThrow(() -> new ReceptionistException("Receptionista no encontrado"));
    }

    public void deleteById (Long idReceptionist) {
        if (!receptionistRepository.existsById(idReceptionist)){
            throw new ReceptionistException("Receptionista no encontrado");
        }
        receptionistRepository.deleteById(idReceptionist);
    }

}
