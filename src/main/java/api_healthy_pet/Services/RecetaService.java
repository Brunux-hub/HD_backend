package api_healthy_pet.Services;

import api_healthy_pet.DTOs.request.RecetaRequest;
import api_healthy_pet.DTOs.response.RecetaResponse;
import api_healthy_pet.Entities.Receta;
import api_healthy_pet.Entities.RegistroMedico;
import api_healthy_pet.Exceptions.DuplicateResourceException;
import api_healthy_pet.Exceptions.ForbiddenException;
import api_healthy_pet.Exceptions.ResourceNotFoundException;
import api_healthy_pet.Mappers.RecetaMapper;
import api_healthy_pet.Repositories.RecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final RecetaMapper recetaMapper;
    private final RegistroMedicoService registroMedicoService;

    public List<RecetaResponse> findAll() {
        return recetaRepository.findAll().stream()
                .filter(receta -> registroMedicoService.canRead(receta.getRegistroMedico()))
                .map(recetaMapper::toResponse)
                .toList();
    }

    public RecetaResponse findById(Long idReceta) {
        Receta receta = getReceta(idReceta);
        if (!registroMedicoService.canRead(receta.getRegistroMedico())) {
            throw new ForbiddenException("No tiene permisos para acceder a esta receta");
        }
        return recetaMapper.toResponse(receta);
    }

    @Transactional
    public RecetaResponse create(RecetaRequest request) {
        validateNumeroRecetaForCreate(request.getNumeroReceta());
        RegistroMedico registroMedico = registroMedicoService.getRegistroMedico(request.getIdRegistroMedico());
        registroMedicoService.validateWriteAccess(registroMedico);

        Receta receta = new Receta();
        receta.setRegistroMedico(registroMedico);
        receta.setNumeroReceta(request.getNumeroReceta());
        receta.setFechaEmision(LocalDateTime.now());
        return recetaMapper.toResponse(recetaRepository.save(receta));
    }

    @Transactional
    public RecetaResponse update(Long idReceta, RecetaRequest request) {
        Receta receta = getReceta(idReceta);
        validateNumeroRecetaForUpdate(request.getNumeroReceta(), idReceta);
        RegistroMedico registroMedico = registroMedicoService.getRegistroMedico(request.getIdRegistroMedico());
        registroMedicoService.validateWriteAccess(registroMedico);

        receta.setRegistroMedico(registroMedico);
        receta.setNumeroReceta(request.getNumeroReceta());
        return recetaMapper.toResponse(recetaRepository.save(receta));
    }

    public List<RecetaResponse> findByRegistroMedico(Long idRegistroMedico) {
        RegistroMedico registroMedico = registroMedicoService.getRegistroMedico(idRegistroMedico);
        if (!registroMedicoService.canRead(registroMedico)) {
            throw new ForbiddenException("No tiene permisos para acceder a estas recetas");
        }
        return recetaRepository.findByRegistroMedico_IdRegistroMedico(idRegistroMedico).stream()
                .map(recetaMapper::toResponse)
                .toList();
    }

    public Receta getReceta(Long idReceta) {
        return recetaRepository.findById(idReceta)
                .orElseThrow(() -> new ResourceNotFoundException("Receta no encontrada"));
    }

    private void validateNumeroRecetaForCreate(String numeroReceta) {
        if (StringUtils.hasText(numeroReceta) && recetaRepository.existsByNumeroReceta(numeroReceta)) {
            throw new DuplicateResourceException("Ya existe una receta con ese numero");
        }
    }

    private void validateNumeroRecetaForUpdate(String numeroReceta, Long idReceta) {
        if (StringUtils.hasText(numeroReceta)
                && recetaRepository.existsByNumeroRecetaAndIdRecetaNot(numeroReceta, idReceta)) {
            throw new DuplicateResourceException("Ya existe una receta con ese numero");
        }
    }
}
