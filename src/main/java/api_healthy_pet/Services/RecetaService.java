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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecetaService {

    private static final Pattern NUMERO_RECETA_PATTERN = Pattern.compile("^REC-?(\\d{4})-(\\d+)$");

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
        RegistroMedico registroMedico = registroMedicoService.getRegistroMedico(request.getIdRegistroMedico());
        registroMedicoService.validateWriteAccess(registroMedico);

        Receta receta = new Receta();
        receta.setRegistroMedico(registroMedico);
        receta.setFechaEmision(LocalDateTime.now());
        receta.setNumeroReceta(generateNumeroReceta(LocalDate.now().getYear()));
        return recetaMapper.toResponse(recetaRepository.save(receta));
    }

    @Transactional
    public RecetaResponse update(Long idReceta, RecetaRequest request) {
        Receta receta = getReceta(idReceta);
        RegistroMedico registroMedico = registroMedicoService.getRegistroMedico(request.getIdRegistroMedico());
        registroMedicoService.validateWriteAccess(registroMedico);

        receta.setRegistroMedico(registroMedico);
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

    private String generateNumeroReceta(int year) {
        int nextCorrelativo = recetaRepository.findAllNumeroReceta().stream()
                .map(numeroReceta -> extractCorrelativo(numeroReceta, year))
                .filter(correlativo -> correlativo > 0)
                .max(Integer::compareTo)
                .orElse(0) + 1;

        String numeroReceta = "REC-%d-%03d".formatted(year, nextCorrelativo);
        if (recetaRepository.existsByNumeroReceta(numeroReceta)) {
            throw new DuplicateResourceException("Ya existe una receta con ese numero");
        }
        return numeroReceta;
    }

    private int extractCorrelativo(String numeroReceta, int expectedYear) {
        Matcher matcher = NUMERO_RECETA_PATTERN.matcher(numeroReceta);
        if (!matcher.matches()) {
            return -1;
        }

        int year = Integer.parseInt(matcher.group(1));
        if (year != expectedYear) {
            return -1;
        }

        return Integer.parseInt(matcher.group(2));
    }
}
