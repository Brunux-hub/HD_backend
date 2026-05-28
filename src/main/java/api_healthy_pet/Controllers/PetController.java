package api_healthy_pet.Controllers;

import api_healthy_pet.Dtos.Request.OwnerRequest;
import api_healthy_pet.Dtos.Request.PetRequest;
import api_healthy_pet.Dtos.Response.OwnerResponse;
import api_healthy_pet.Dtos.Response.PetResponse;
import api_healthy_pet.Services.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    public ResponseEntity<PetResponse> createOwner(@Valid @RequestBody PetRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(petService.create(request));
    }
    @GetMapping("/{idPet}")
    public ResponseEntity<PetResponse> getOwnerByID(@PathVariable Long idPet){
        return ResponseEntity.ok().body(petService.findById(idPet));
    }

    @GetMapping
    public ResponseEntity<List<PetResponse>> getAllOwners(){
        return ResponseEntity.ok().body(petService.findAll());
    }

    @PutMapping("/{idPet}")
    public ResponseEntity<PetResponse> updateOwnerById(@PathVariable Long idPet, @Valid @RequestBody PetRequest request){
        return ResponseEntity.ok().body(petService.updateById(idPet, request));
    }

    @DeleteMapping("/{idPet}")
    public ResponseEntity<Void> deleteOwnerById(@PathVariable Long idPet){
        petService.deleteById(idPet);
        return ResponseEntity.noContent().build();
    }

}
