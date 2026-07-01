package api_healthy_pet.Controllers;

import api_healthy_pet.Dtos.Request.OwnerRequest;
import api_healthy_pet.Dtos.Request.ReceptionistRequest;
import api_healthy_pet.Dtos.Response.OwnerResponse;
import api_healthy_pet.Dtos.Response.ReceptionistResponse;
import api_healthy_pet.Services.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping
    public ResponseEntity<OwnerResponse> createOwner(@Valid @RequestBody OwnerRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ownerService.create(request));
    }
    // Owner del usuario logueado (para saber, tras el login, si es cliente).
    // 200 + Owner si es cliente; 204 si no lo es (staff).
    @GetMapping("/me")
    public ResponseEntity<OwnerResponse> getMyOwner(Principal principal){
        return ownerService.findByUsername(principal.getName())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/{idOwner}")
    public ResponseEntity<OwnerResponse> getOwnerByID(@PathVariable Long idOwner){
        return ResponseEntity.ok().body(ownerService.findById(idOwner));
    }

    @GetMapping
    public ResponseEntity<List<OwnerResponse>> getAllOwners(){
        return ResponseEntity.ok().body(ownerService.findAll());
    }

    @PutMapping("/{idOwner}")
    public ResponseEntity<OwnerResponse> updateOwnerById(@PathVariable Long idOwner, @Valid @RequestBody OwnerRequest request){
        return ResponseEntity.ok().body(ownerService.updateById(idOwner, request));
    }

    @DeleteMapping("/{idOwner}")
    public ResponseEntity<Void> deleteOwnerById(@PathVariable Long idOwner){
        ownerService.deleteById(idOwner);
        return ResponseEntity.noContent().build();
    }

}
