package api_healthy_pet.Controllers;

import api_healthy_pet.Dtos.Request.VeterinarianRequest;
import api_healthy_pet.Dtos.Response.VeterinarianResponse;
import api_healthy_pet.Services.VeterinarianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/veterinarian")
@RequiredArgsConstructor
public class VeterinarianController {

    private final VeterinarianService veterinarianService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VeterinarianResponse> createVeterinarian(@Valid @RequestBody VeterinarianRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(veterinarianService.create(request));
    }
    @GetMapping("/{idVeterinarian}")
    public ResponseEntity<VeterinarianResponse> getVeterinarianById(@PathVariable Long idVeterinarian){
        return ResponseEntity.ok().body(veterinarianService.findById(idVeterinarian));
    }

    @GetMapping
    public ResponseEntity<List<VeterinarianResponse>> getAllVeterinarians(){
        return ResponseEntity.ok().body(veterinarianService.findAll());
    }

    @GetMapping("/available")
    public ResponseEntity<List<VeterinarianResponse>> getAllAvailableVeterinarians (@RequestParam LocalDateTime date){
        return ResponseEntity.ok().body(veterinarianService.findAllAvailable(date));
    }

    @PutMapping("/{idVeterinarian}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VeterinarianResponse> updateVeterinarian(@PathVariable Long idVeterinarian, @Valid @RequestBody VeterinarianRequest request){
        return ResponseEntity.ok().body(veterinarianService.updateById(idVeterinarian, request));
    }

    @DeleteMapping("/{idVeterinarian}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteVeterinarianById(@PathVariable Long idVeterinarian){
        veterinarianService.deleteById(idVeterinarian);
        return ResponseEntity.noContent().build();
    }

}
