package api_healthy_pet.Services;

import api_healthy_pet.DTOs.request.ServicioRequest;
import api_healthy_pet.DTOs.response.ServicioResponse;
import api_healthy_pet.Entities.Servicio;
import api_healthy_pet.Exceptions.ResourceNotFoundException;
import api_healthy_pet.Mappers.ServicioMapper;
import api_healthy_pet.Repositories.ServicioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ServicioService {

    private final ServicioRepository servicioRepository;
    private final ServicioMapper servicioMapper;

    public List<ServicioResponse> findAll() {
        return servicioRepository.findAll().stream()
                .map(servicioMapper::toResponse)
                .toList();
    }

    public ServicioResponse findById(Long idServicio) {
        return servicioMapper.toResponse(getServicio(idServicio));
    }

    @Transactional
    public ServicioResponse create(ServicioRequest request) {
        Servicio servicio = new Servicio();
        applyRequest(servicio, request);
        return servicioMapper.toResponse(servicioRepository.save(servicio));
    }

    @Transactional
    public ServicioResponse update(Long idServicio, ServicioRequest request) {
        Servicio servicio = getServicio(idServicio);
        applyRequest(servicio, request);
        return servicioMapper.toResponse(servicioRepository.save(servicio));
    }

    @Transactional
    public void activate(Long idServicio) {
        Servicio servicio = getServicio(idServicio);
        servicio.setActivo(Boolean.TRUE);
        servicioRepository.save(servicio);
    }

    @Transactional
    public void deactivate(Long idServicio) {
        Servicio servicio = getServicio(idServicio);
        servicio.setActivo(Boolean.FALSE);
        servicioRepository.save(servicio);
    }

    public Servicio getServicio(Long idServicio) {
        return servicioRepository.findById(idServicio)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado"));
    }

    private void applyRequest(Servicio servicio, ServicioRequest request) {
        servicio.setNombre(request.getNombre());
        servicio.setDescripcion(request.getDescripcion());
        servicio.setPrecio(request.getPrecio());
        if (request.getActivo() != null) {
            servicio.setActivo(request.getActivo());
        } else if (servicio.getIdServicio() == null) {
            servicio.setActivo(Boolean.TRUE);
        }
    }
}
