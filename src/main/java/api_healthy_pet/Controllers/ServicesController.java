package api_healthy_pet.Controllers;

import api_healthy_pet.Dtos.Request.PetRequest;
import api_healthy_pet.Dtos.Request.ServiceRequest;
import api_healthy_pet.Dtos.Response.PetResponse;
import api_healthy_pet.Dtos.Response.ServiceResponse;
import api_healthy_pet.Entities.Services;
import api_healthy_pet.Services.ServicesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServicesController {

    private final ServicesService service;

    @PostMapping
    public ResponseEntity<ServiceResponse> createService(@Valid @RequestBody ServiceRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }
    @GetMapping("/{idService}")
    public ResponseEntity<ServiceResponse> getServiceById(@PathVariable Long idService){
        return ResponseEntity.ok().body(service.findById(idService));
    }

    @GetMapping
    public ResponseEntity<List<ServiceResponse>> getAllServices(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @PutMapping("/{idService}")
    public ResponseEntity<ServiceResponse> updateServiceById(@PathVariable Long idService, @Valid @RequestBody ServiceRequest request){
        return ResponseEntity.ok().body(service.updateById(idService, request));
    }

    @DeleteMapping("/{idService}")
    public ResponseEntity<Void> deleteServiceById(@PathVariable Long idService){
        service.deleteById(idService);
        return ResponseEntity.noContent().build();
    }

}
