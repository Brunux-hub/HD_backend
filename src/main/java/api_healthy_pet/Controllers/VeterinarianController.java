package api_healthy_pet.Controllers;

import api_healthy_pet.Dtos.Request.UserRequest;
import api_healthy_pet.Dtos.Request.VeterinarianRequest;
import api_healthy_pet.Dtos.Response.UserResponse;
import api_healthy_pet.Dtos.Response.VeterinarianResponse;
import api_healthy_pet.Services.VeterinarianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veterinarian")
@RequiredArgsConstructor
public class VeterinarianController {

    private final VeterinarianService veterinarianService;

    @PostMapping
    public ResponseEntity<VeterinarianResponse> createVeterinarian(@Valid @RequestBody VeterinarianRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(veterinarianService.createUser(request));
    }
    @GetMapping("/{idVeterinarian}")
    public ResponseEntity<VeterinarianResponse> getVeterinarianById(@PathVariable Long idVeterinarian){
        return ResponseEntity.ok().body(veterinarianService.findById(idVeterinarian));
    }

    @GetMapping
    public ResponseEntity<List<VeterinarianResponse>> getAllVeterinarian(){
        return ResponseEntity.ok().body(veterinarianService.findAll());
    }

    @PutMapping("/{idVeterinarian}")
    public ResponseEntity<VeterinarianResponse> updateVeterinarian(@PathVariable Long idVeterinarian, @Valid @RequestBody VeterinarianRequest request){
        return ResponseEntity.ok().body(veterinarianService.updateById(idVeterinarian, request));
    }

    @DeleteMapping("/{idVeterinarian}")
    public ResponseEntity<Void> deleteVeterinarianById(@PathVariable Long idVeterinarian){
        veterinarianService.deleteVeterinarianById(idVeterinarian);
        return ResponseEntity.noContent().build();
    }

}
