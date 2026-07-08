package api_healthy_pet.Controllers;

import api_healthy_pet.Dtos.Request.VeterinarioRequest;
import api_healthy_pet.Dtos.Response.VeterinarioResponse;
import api_healthy_pet.Services.VeterinarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/veterinarios")
@RequiredArgsConstructor
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    @PostMapping
    public ResponseEntity<VeterinarioResponse> createVeterinario(@Valid @RequestBody VeterinarioRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(veterinarioService.create(request));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<VeterinarioResponse> getVeterinarioById(@PathVariable Long userId){
        return ResponseEntity.ok().body(veterinarioService.findById(userId));
    }

    @GetMapping
    public ResponseEntity<List<VeterinarioResponse>> getAllVeterinarios(){
        return ResponseEntity.ok().body(veterinarioService.findAll());
    }

    @GetMapping("/available")
    public ResponseEntity<List<VeterinarioResponse>> getAllAvailableVeterinarios (@RequestParam LocalDateTime date){
        return ResponseEntity.ok().body(veterinarioService.findAllAvailable(date));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<VeterinarioResponse> updateVeterinario(@PathVariable Long userId, @Valid @RequestBody VeterinarioRequest request){
        return ResponseEntity.ok().body(veterinarioService.updateById(userId, request));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteVeterinarioById(@PathVariable Long userId){
        veterinarioService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

}
