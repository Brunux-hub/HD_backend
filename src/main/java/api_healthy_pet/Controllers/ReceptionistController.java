package api_healthy_pet.Controllers;

import api_healthy_pet.Dtos.Request.ReceptionistRequest;
import api_healthy_pet.Dtos.Response.ReceptionistResponse;
import api_healthy_pet.Services.ReceptionistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receptionist")
@RequiredArgsConstructor
public class ReceptionistController {

    private final ReceptionistService receptionistService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReceptionistResponse> createReceptionist(@Valid @RequestBody ReceptionistRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(receptionistService.create(request));
    }
    @GetMapping("/{idReceptionist}")
    public ResponseEntity<ReceptionistResponse> getReceptionistByID(@PathVariable Long idReceptionist){
        return ResponseEntity.ok().body(receptionistService.findById(idReceptionist));
    }

    @GetMapping
    public ResponseEntity<List<ReceptionistResponse>> getAllReceptionist(){
        return ResponseEntity.ok().body(receptionistService.findAll());
    }

    @PutMapping("/{idReceptionist}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReceptionistResponse> updateReceptionistById(@PathVariable Long idReceptionist, @Valid @RequestBody ReceptionistRequest request){
        return ResponseEntity.ok().body(receptionistService.updateById(idReceptionist, request));
    }

    @DeleteMapping("/{idReceptionist}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteReceptionistById(@PathVariable Long idReceptionist){
        receptionistService.deleteById(idReceptionist);
        return ResponseEntity.noContent().build();
    }

}
