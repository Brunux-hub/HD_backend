package api_healthy_pet.Controllers;

import api_healthy_pet.Dtos.Request.RecepcionistaRequest;
import api_healthy_pet.Dtos.Response.RecepcionistaResponse;
import api_healthy_pet.Services.RecepcionistaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recepcionistas")
@RequiredArgsConstructor
public class RecepcionistaController {

    private final RecepcionistaService recepcionistaService;

    @PostMapping
    public ResponseEntity<RecepcionistaResponse> createRecepcionista(@Valid @RequestBody RecepcionistaRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(recepcionistaService.create(request));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<RecepcionistaResponse> getRecepcionistaById(@PathVariable Long userId){
        return ResponseEntity.ok().body(recepcionistaService.findById(userId));
    }

    @GetMapping
    public ResponseEntity<List<RecepcionistaResponse>> getAllRecepcionistas(){
        return ResponseEntity.ok().body(recepcionistaService.findAll());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<RecepcionistaResponse> updateRecepcionistaById(@PathVariable Long userId, @Valid @RequestBody RecepcionistaRequest request){
        return ResponseEntity.ok().body(recepcionistaService.updateById(userId, request));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteRecepcionistaById(@PathVariable Long userId){
        recepcionistaService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

}
