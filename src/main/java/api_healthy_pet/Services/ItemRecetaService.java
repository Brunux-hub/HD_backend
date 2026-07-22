package api_healthy_pet.Services;

import api_healthy_pet.DTOs.request.ItemRecetaRequest;
import api_healthy_pet.DTOs.response.ItemRecetaResponse;
import api_healthy_pet.Entities.ItemReceta;
import api_healthy_pet.Entities.Receta;
import api_healthy_pet.Exceptions.ForbiddenException;
import api_healthy_pet.Exceptions.ResourceNotFoundException;
import api_healthy_pet.Mappers.ItemRecetaMapper;
import api_healthy_pet.Repositories.ItemRecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemRecetaService {

    private final ItemRecetaRepository itemRecetaRepository;
    private final ItemRecetaMapper itemRecetaMapper;
    private final RecetaService recetaService;
    private final RegistroMedicoService registroMedicoService;

    public List<ItemRecetaResponse> findAll() {
        return itemRecetaRepository.findAll().stream()
                .filter(item -> registroMedicoService.canRead(item.getReceta().getRegistroMedico()))
                .map(itemRecetaMapper::toResponse)
                .toList();
    }

    public ItemRecetaResponse findById(Long idItemReceta) {
        ItemReceta itemReceta = getItemReceta(idItemReceta);
        if (!registroMedicoService.canRead(itemReceta.getReceta().getRegistroMedico())) {
            throw new ForbiddenException("No tiene permisos para acceder a este item de receta");
        }
        return itemRecetaMapper.toResponse(itemReceta);
    }

    @Transactional
    public ItemRecetaResponse create(ItemRecetaRequest request) {
        Receta receta = recetaService.getReceta(request.getIdReceta());
        registroMedicoService.validateWriteAccess(receta.getRegistroMedico());

        ItemReceta itemReceta = new ItemReceta();
        applyRequest(itemReceta, request, receta);
        return itemRecetaMapper.toResponse(itemRecetaRepository.save(itemReceta));
    }

    @Transactional
    public ItemRecetaResponse update(Long idItemReceta, ItemRecetaRequest request) {
        ItemReceta itemReceta = getItemReceta(idItemReceta);
        Receta receta = recetaService.getReceta(request.getIdReceta());
        registroMedicoService.validateWriteAccess(receta.getRegistroMedico());

        applyRequest(itemReceta, request, receta);
        return itemRecetaMapper.toResponse(itemRecetaRepository.save(itemReceta));
    }

    public List<ItemRecetaResponse> findByReceta(Long idReceta) {
        Receta receta = recetaService.getReceta(idReceta);
        if (!registroMedicoService.canRead(receta.getRegistroMedico())) {
            throw new ForbiddenException("No tiene permisos para acceder a estos items de receta");
        }
        return itemRecetaRepository.findByReceta_IdReceta(idReceta).stream()
                .map(itemRecetaMapper::toResponse)
                .toList();
    }

    public ItemReceta getItemReceta(Long idItemReceta) {
        return itemRecetaRepository.findById(idItemReceta)
                .orElseThrow(() -> new ResourceNotFoundException("Item de receta no encontrado"));
    }

    private void applyRequest(ItemReceta itemReceta, ItemRecetaRequest request, Receta receta) {
        itemReceta.setReceta(receta);
        itemReceta.setMedicamento(request.getMedicamento());
        itemReceta.setCantidad(request.getCantidad());
        itemReceta.setDosis(request.getDosis());
        itemReceta.setIndicaciones(request.getIndicaciones());
    }
}
