package api_healthy_pet.Services;

import api_healthy_pet.Dtos.Request.ServiceRequest;
import api_healthy_pet.Dtos.Response.ServiceResponse;
import api_healthy_pet.Entities.Services;
import api_healthy_pet.Exceptions.ServiceException;
import api_healthy_pet.Mappers.ServiceMapper;
import api_healthy_pet.Repositories.ServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicesService {

    private final ServiceMapper serviceMapper;
    private final ServicesRepository servicesRepository;

    public ServiceResponse create (ServiceRequest request){
        return serviceMapper.toResponse(servicesRepository.save(serviceMapper.toEntity(request)));
    }

    public ServiceResponse findById (Long idService){
        return servicesRepository.findById(idService)
                .map(serviceMapper::toResponse)
                .orElseThrow(() -> new ServiceException("Servicio no encontrado"));
    }

    public List<ServiceResponse> findAll(){
        return servicesRepository.findAll()
                .stream().map(serviceMapper::toResponse).toList();
    }

    public ServiceResponse updateById (Long idService, ServiceRequest request){
         Services service = servicesRepository.findById(idService)
                .orElseThrow(() -> new ServiceException("Servicio no encontrado"));

        serviceMapper.updateEntityFromRequest(request, service);

        return serviceMapper.toResponse(servicesRepository.save(service));
    }

    public void deleteById (Long idService) {
        if (!servicesRepository.existsById(idService)){
            throw new ServiceException("Servicio no encontrado");
        }
        servicesRepository.deleteById(idService);
    }

}
