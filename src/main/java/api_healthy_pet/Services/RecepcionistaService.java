package api_healthy_pet.Services;

import api_healthy_pet.DTOs.request.RecepcionistaRequest;
import api_healthy_pet.DTOs.response.RecepcionistaResponse;
import api_healthy_pet.Entities.Recepcionista;
import api_healthy_pet.Entities.Usuario;
import api_healthy_pet.Enums.RolUsuario;
import api_healthy_pet.Exceptions.ResourceNotFoundException;
import api_healthy_pet.Mappers.RecepcionistaMapper;
import api_healthy_pet.Repositories.RecepcionistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecepcionistaService {

    private final RecepcionistaRepository recepcionistaRepository;
    private final RecepcionistaMapper recepcionistaMapper;
    private final AccountServiceSupport accountServiceSupport;

    public List<RecepcionistaResponse> findAll() {
        return recepcionistaRepository.findAll().stream()
                .map(recepcionistaMapper::toResponse)
                .toList();
    }

    public RecepcionistaResponse findById(Long idUsuario) {
        return recepcionistaMapper.toResponse(getRecepcionista(idUsuario));
    }

    @Transactional
    public RecepcionistaResponse create(RecepcionistaRequest request) {
        Usuario usuario = accountServiceSupport.createUsuario(
                request.getCorreo(),
                request.getContrasenia(),
                RolUsuario.RECEPCIONISTA,
                request.getHabilitado()
        );

        Recepcionista recepcionista = new Recepcionista();
        recepcionista.setUsuario(usuario);
        recepcionista.setNombres(request.getNombres());
        recepcionista.setApellidos(request.getApellidos());
        recepcionista.setTelefono(request.getTelefono());
        return recepcionistaMapper.toResponse(recepcionistaRepository.save(recepcionista));
    }

    @Transactional
    public RecepcionistaResponse update(Long idUsuario, RecepcionistaRequest request) {
        Recepcionista recepcionista = getRecepcionista(idUsuario);
        accountServiceSupport.updateUsuario(
                recepcionista.getUsuario(),
                request.getCorreo(),
                request.getContrasenia(),
                request.getHabilitado()
        );
        recepcionista.setNombres(request.getNombres());
        recepcionista.setApellidos(request.getApellidos());
        recepcionista.setTelefono(request.getTelefono());
        return recepcionistaMapper.toResponse(recepcionistaRepository.save(recepcionista));
    }

    public Recepcionista getRecepcionista(Long idUsuario) {
        return recepcionistaRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Recepcionista no encontrado"));
    }
}
